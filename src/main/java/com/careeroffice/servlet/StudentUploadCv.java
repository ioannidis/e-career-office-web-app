package com.careeroffice.servlet;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.sql.DataSource;

import com.careeroffice.model.*;
import com.careeroffice.service.AuthService;
import com.careeroffice.service.KeywordService;
import com.careeroffice.service.factory.ServiceEnum;
import com.careeroffice.service.factory.ServiceFactory;
import com.careeroffice.service.pivot.KeywordCvPivotService;
import com.careeroffice.service.CvService;


/**
 * Handles CV, Keywords uploads
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
        /**
         * Initializing the needed services
         */
        AuthService authService = new AuthService(request.getSession());

        if (!authService.isLoggedIn()) {
            response.sendRedirect("login");
            return;
        }

        if (!authService.hasRole("u_student") && !authService.hasRole("p_student")) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        KeywordService keywordService = (KeywordService) ServiceFactory.getService(ServiceEnum.KeywordService);
        KeywordCvPivotService keywordCvPivotService = (KeywordCvPivotService) ServiceFactory.getService(ServiceEnum.KeywordCvPivotService);
        CvService cvService = (CvService) ServiceFactory.getService(ServiceEnum.CvService);

        /**
         * Gets the username and the CV of the user
         * The CV could be null, if it isn't uploaded
         */
        User user = authService.getUser();
        String username = user.getUsername();
        Cv cv = cvService.findOne(username);

        /**
         * Gets the keywords of the user, could be null
         */
        List<Keyword> keywords = keywordService.findAll();

        /**
         * If a CV exists
         */
        if (cv != null) {
            List<Keyword> checkedKeywords = keywordCvPivotService.findByCvId(cv.getId());
            /**
             * If a CV exists and keywords are empty
             */
            if (checkedKeywords == null) {
                request.setAttribute("keywords", keywords);
                request.getRequestDispatcher("WEB-INF/views/student/upload_cv.jsp").forward(request, response);
                return;
            }

            List<CheckedUserKeyword> pivotTable = new ArrayList<>();

            /**
             * If a CV exists and keywords are not empty
             * Show the both the checked and the unckecked keywords
             */
            for (Keyword keyword: keywords) {
                if (checkedKeywords.contains(keyword)) {
                    pivotTable.add(new CheckedUserKeyword(keyword.getTitle(), keyword.getSlug(), true));
                } else {
                    pivotTable.add(new CheckedUserKeyword(keyword.getTitle(), keyword.getSlug(), false));
                }

            }
            request.setAttribute("cvName", cv.getFileUrl());
            request.setAttribute("pivotTable", pivotTable);
            request.getRequestDispatcher("WEB-INF/views/student/upload_cv.jsp").forward(request, response);
            return;
        }

        request.setAttribute("keywords", keywords);
        request.getRequestDispatcher("WEB-INF/views/student/upload_cv.jsp").forward(request, response);
    }

    /**
     * Handles all POST requests.
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        /**
         * Initializing the needed services
         */
        CvService cvService = (CvService) ServiceFactory.getService(ServiceEnum.CvService);
        KeywordService keywordService = (KeywordService) ServiceFactory.getService(ServiceEnum.KeywordService);
        KeywordCvPivotService keywordCvPivotService = (KeywordCvPivotService) ServiceFactory.getService(ServiceEnum.KeywordCvPivotService);
        AuthService authService = new AuthService(request.getSession());

        /**
         * Gets the username
         */
        User user = authService.getUser();
        String username = user.getUsername();

        /**
         * Receives FORM data
         */
        String[] keywords = request.getParameterValues("keywords");
        Part part = request.getPart("file");

        // Create cv data for first time
        // else update cv data
        if (cvService.findOne(username) == null) {

            cvService.save( username, username + "_" + part.getSubmittedFileName() );
            saveKeywords(keywords, user);


        } else {
            Cv cv = cvService.findOne(username);

            eraseKeywords(user);
            saveKeywords(keywords, user);

            if (!(username + "_" + part.getSubmittedFileName()).equals(cv.getFileUrl()) && !part.getSubmittedFileName().equals( "" )) {
                writeFileToDisk(part, username);
                cvService.update( new Cv(username, username + "_" + part.getSubmittedFileName()) );
            }

        }

        request.getRequestDispatcher(user.getRoleId()).forward(request, response);

//        if (keywords.length > 0) {
//            eraseKeywords(user);
//            saveKeywords(keywords, user);
//        }
//
//
//        boolean hasError = false;
//
//        if (part.getSubmittedFileName().equals("")) {
//            request.getSession().setAttribute("cvError", true);
//            hasError = true;
//            doGet(request, response);
//            return;
//        }
//
//        String fileUrl = savedPathCalculator(username).toString();
//
//        if (cvService.findOne(username) == null) {
//            cvService.save(username, fileUrl);
//        } else {
//            cvService.update(new Cv(username, fileUrl));
//        }
//
//        writeFileToDisk(part, username);


//        if (!isFirstTime(username) && part.getSubmittedFileName() == "") {
//            eraseKeywords(user);
//            saveKeywords(keywords, user);
//        } else {
//            if (part.getSubmittedFileName() == "") {
//                request.getSession().setAttribute("cvError", true);
//                hasError = true;
//            }
//
//            if (hasError) {
//                doGet(request, response);
//                return;
//            }
//
//            String fileUrl = savedPathCalculator(username).toString();
//
//            if (cvService.findOne(username) == null) {
//                cvService.save(username, fileUrl);
//            } else {
//                cvService.update(new Cv(username, fileUrl));
//            }
//
//            writeFileToDisk(part, username);
//
//            eraseKeywords(user);
//            saveKeywords(keywords, user);
//        }

//        request.getRequestDispatcher(user.getRoleId()).forward(request, response);
    }

    /**
     * This method receives a user object and erase all its keywords
     * @param part The data received from the multipart/form-data POST request. The pdf data
     */
    private void writeFileToDisk(Part part, String username)
            throws IOException{

        Path savePath = savedPathCalculator(username, part.getSubmittedFileName());

        // Creates the save directory if it does not exists
        File fileSavedDir = new File(SAVE_DIR);

        /**
         * Creates a directory for the uploaded files
         */
        if (!fileSavedDir.exists()) {
            fileSavedDir.mkdir();
        }

        try (InputStream input = part.getInputStream()) {
            /**
             * Overwrites the pdf file if already exists
             */
            Files.deleteIfExists(savePath);
            Files.copy(input, savePath);
        }
    }

    /**
     * Calculates the final path to the saved .pdf
     * The name of the file is username.pdf
     */
    private Path savedPathCalculator(String username, String submittedFile) {
        String fileName = username + "_" + submittedFile;
        return new File(SAVE_DIR, fileName).toPath();
    }

    /**
     * Checks if a user has uploaded a CV
     * @param username
     * @return True if a user has uploaded a CV
     */
    private boolean isFirstTime(String username) {
        CvService cvService = (CvService) ServiceFactory.getService(ServiceEnum.CvService);
        if (cvService.findOne(username) == null) {
            return true;
        }
        return false;
    }

    /**
     * This method receives a user object and erase all its keywords
     * @param keywords The list of keywords to associate with a specific User's CV
     * @param user A user object from the class User
     */
    private void saveKeywords(String[] keywords, User user) {
        KeywordService keywordService = (KeywordService) ServiceFactory.getService(ServiceEnum.KeywordService);
        KeywordCvPivotService keywordCvPivotService = (KeywordCvPivotService) ServiceFactory.getService(ServiceEnum.KeywordCvPivotService);
        CvService cvService = (CvService) ServiceFactory.getService(ServiceEnum.CvService);

        /**
         * If keywords have been supplied, save them
         */
        if (keywords != null) {
            for (String keyword: keywords) {
                int keywordId = keywordService.findKeywordByTitle(keyword).getId();
                keywordCvPivotService.save(new KeywordCvPivot(keywordId, cvService.findOne(user.getUsername()).getId()));
            }
        }
    }

    /**
     * This method receives a user object and erase all its keywords
     * @param user A user object from the class User
     */
    private void eraseKeywords(User user) {
        KeywordCvPivotService keywordCvPivotService = (KeywordCvPivotService) ServiceFactory.getService(ServiceEnum.KeywordCvPivotService);
        CvService cvService = (CvService) ServiceFactory.getService(ServiceEnum.CvService);

        int cvId = cvService.findOne(user.getUsername()).getId();
        keywordCvPivotService.deleteByCvId(cvId);
    }
}