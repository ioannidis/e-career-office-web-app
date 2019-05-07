package com.careeroffice.servlet;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.sql.DataSource;

import com.careeroffice.model.Cv;
import com.careeroffice.model.Keyword;
import com.careeroffice.model.KeywordCvPivot;
import com.careeroffice.model.User;
import com.careeroffice.service.AuthService;
import com.careeroffice.service.KeywordService;
import com.careeroffice.service.factory.ServiceEnum;
import com.careeroffice.service.factory.ServiceFactory;
import com.careeroffice.service.pivot.KeywordCvPivotService;
import com.careeroffice.service.CvService;


/**
 * Handles login requests and responses.
 */
@WebServlet({"/StudentUploadCv", "/upload_cv"})
@MultipartConfig(fileSizeThreshold=1024*1024*2, // 2MB
        maxFileSize=1024*1024*10,      // 10MB
        maxRequestSize=1024*1024*50)   // 50MB
public class StudentUploadCv extends HttpServlet {

    /**
     * Java related serial version UID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Name of the directory where uploaded files will be saved, relative to
     * the web application directory.
     */
    private static final String SAVE_DIR = System.getProperty("user.home") + "/Desktop/uploads";

    /**
     * An instance of the database connection.
     */
    @Resource(name = "jdbc/career_office")
    private DataSource ds;

    /**
     * Handles all GET requests.
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CvService cvService = (CvService) ServiceFactory.getService(ServiceEnum.CvService);
        KeywordCvPivotService keywordCvPivotService = (KeywordCvPivotService) ServiceFactory.getService(ServiceEnum.KeywordCvPivotService);

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        Cv cv = cvService.findOne(user.getUsername());

        if (cv != null) {
            List<Keyword> keywords = keywordCvPivotService.findByCvId(cv.getId());
            request.setAttribute("keywords", keywords);
            request.setAttribute("user", user);
        }

        request.getRequestDispatcher("WEB-INF/views/student/upload_cv.jsp").forward(request, response);

    }

    /**
     * Handles all POST requests.
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        CvService cvService = (CvService) ServiceFactory.getService(ServiceEnum.CvService);
        KeywordService keywordService = (KeywordService) ServiceFactory.getService(ServiceEnum.KeywordService);
        KeywordCvPivotService keywordCvPivotService = (KeywordCvPivotService) ServiceFactory.getService(ServiceEnum.KeywordCvPivotService);

        // Retrieves the CV keyword tags
        String[] keywords = request.getParameterValues("keywords");
        // Retrieves the uploaded CV <input type="file" name="file">
        Part part = request.getPart("file");

        AuthService authService = new AuthService(request.getSession());
        User user = authService.getUser();
        String username = user.getUsername();

        String fileUrl = savedPathCalculator(username).toString();

        // If CV doesn't exist
        if (cvService.findOne(username) == null) {
            cvService.save(username, fileUrl);
        } else {
            cvService.update(new Cv(username, fileUrl));
        }
        writeFileToDisk(part, username);

        int cvId = cvService.findOne(user.getUsername()).getId();

        // Erase keyword - cv table rows for specific user
        keywordCvPivotService.deleteByCvId(cvId);

        for (String keyword: keywords) {
            if (keywordService.findKeywordByTitle(keyword) != null) {
                int keywordId = keywordService.findKeywordByTitle(keyword).getId();
                keywordCvPivotService.save(new KeywordCvPivot(keywordId, cvId));
            } else {
                int keywordId = keywordService.saveReturn(keyword, keyword).getId();
                keywordCvPivotService.save(new KeywordCvPivot(keywordId, cvId));
            }
        }

        request.getRequestDispatcher(user.getRoleId()).forward(request, response);
    }

    private void writeFileToDisk(Part part, String username)
            throws IOException{

        Path savePath = savedPathCalculator(username);

        // Creates the save directory if it does not exists
        File fileSavedDir = new File(SAVE_DIR);

        if (!fileSavedDir.exists()) {
            fileSavedDir.mkdir();
        }

        try (InputStream input = part.getInputStream()) {
            Files.deleteIfExists(savePath);
            Files.copy(input, savePath);

        }
    }

    private Path savedPathCalculator(String username) {
        String fileName = username + ".pdf";
        return new File(SAVE_DIR, fileName).toPath();
    }
}