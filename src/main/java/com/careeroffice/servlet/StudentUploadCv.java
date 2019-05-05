package com.careeroffice.servlet;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.sql.DataSource;

import com.careeroffice.model.Cv;
import com.careeroffice.model.User;
import com.careeroffice.service.factory.ServiceEnum;
import com.careeroffice.service.factory.ServiceFactory;
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

        request.getRequestDispatcher("WEB-INF/views/student/upload_cv.jsp").forward(request, response);

    }

    /**
     * Handles all POST requests.
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        CvService cvService = (CvService) ServiceFactory.getService(ServiceEnum.CvService);

        // Retrieves the CV keyword tags
        String[] keywords = request.getParameterValues("keywords");

        // Retrieves the uploaded CV <input type="file" name="file">
        Part part = request.getPart("file");

        // Gets the absolute path of the deployed application
        String appPath = request.getServletContext().getRealPath("/uploads");

        // Used for getting the logined user USERNAME and save the pdf with his name
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String username = user.getUsername();

        String fileUrl = savedPathCalculator(username).toString();

        // If a CV for a specific user doesn't exist
        if (cvService.findOne(username) == null) {
            cvService.save(username, fileUrl);
            writeFileToDisk(part, username);
            request.getRequestDispatcher("WEB-INF/views/student/index.jsp").forward(request, response);
        }

        cvService.update(new Cv(username, fileUrl));

        // Write the uploaded CV(pdf) to disk
        writeFileToDisk(part, username);

        request.getRequestDispatcher("WEB-INF/views/student/index.jsp").forward(request, response);
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
