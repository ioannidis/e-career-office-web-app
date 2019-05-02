package com.careeroffice.servlet;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;


/**
 * Handles login requests and responses.
 */
@WebServlet({"/StudentDownloadCv", "/get_cv"})
public class StudentDownloadCv extends HttpServlet {

    /**
     * Java related serial version UID.
     */
    private static final long serialVersionUID = 1L;

    private static final String SAVE_DIR = System.getProperty("user.home") + "/Desktop/uploads";

    /**
     * Handles all GET requests.
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String filePath = SAVE_DIR + File.separator + "test.pdf";
        response.setHeader("Content-Disposition", "attachment; filename="+filePath+";");

    }

    /**
     * Handles all POST requests.
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String filePath = request.getServletContext().getRealPath("") + File.separator + "uploadFiles" + "test.pdf";
        response.setHeader("Content-Disposition", "attachment; filename="+filePath+";");

    }
}
