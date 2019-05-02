package com.careeroffice.servlet;

import com.careeroffice.model.User;

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
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String username = user.getUsername();

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

    }

    /**
     * Handles all POST requests.
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String username = user.getUsername();

        String filePath = SAVE_DIR + File.separator + username + ".pdf";
        response.setHeader("Content-Disposition", "attachment; filename="+filePath+";");

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
