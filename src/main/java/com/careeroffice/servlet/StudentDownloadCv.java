package com.careeroffice.servlet;

import com.careeroffice.dao.factory.DaoEnum;
import com.careeroffice.dao.factory.DaoFactory;
import com.careeroffice.model.User;
import com.careeroffice.service.AuthService;
import com.careeroffice.service.CvService;
import com.careeroffice.service.factory.ServiceEnum;
import com.careeroffice.service.factory.ServiceFactory;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
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

        // Gets username
        AuthService authService = new AuthService(request.getSession());
        CvService cvService = (CvService) ServiceFactory.getService(ServiceEnum.CvService);
        User user = authService.getUser();
        String username = user.getUsername();

        if (cvService.findOne(username) != null) {
            String filePath = SAVE_DIR + File.separator + username + ".pdf";


            byte[] outputBytes = loadFile(filePath);

            response.setHeader("Pragma", "no-cache");
            response.setHeader("Cache-control", "private");
            response.setDateHeader("Expires", 0);
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=\"test.pdf\"");

            if (outputBytes != null) {
                response.setContentLength(outputBytes.length);
                ServletOutputStream out = response.getOutputStream();
                out.write(outputBytes);
                out.flush();
                out.close();
            }
        } else {
            request.setAttribute("noCv", true);
            request.getRequestDispatcher(user.getRoleId()).forward(request, response);
        }


    }

    /**
     * Handles all POST requests.
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);

    }

    private static byte[] loadFile(String sourcePath) throws IOException
    {
        InputStream inputStream = null;
        try
        {
            inputStream = new FileInputStream(sourcePath);
            return readFully(inputStream);
        }
        finally
        {
            if (inputStream != null)
            {
                inputStream.close();
            }
        }
    }

    private static byte[] readFully(InputStream stream) throws IOException
    {
        byte[] buffer = new byte[8192];
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        int bytesRead;
        while ((bytesRead = stream.read(buffer)) != -1)
        {
            baos.write(buffer, 0, bytesRead);
        }
        return baos.toByteArray();
    }
}