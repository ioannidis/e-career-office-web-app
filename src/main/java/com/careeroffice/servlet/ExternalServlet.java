package com.careeroffice.servlet;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;

@WebServlet({"/ExternalServlet", "/external"})
public class ExternalServlet {
    /**
     * Java related serial version UID.
     */
    private static final long serialVersionUID = 1L;

//    /**
//     * An instance of the database connection.
//     */
//    @Resource(name = "jdbc/career_office")
//    private DataSource ds;

    /**
     * Handles all GET requests.
     */
    protected void doGet( HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    /**
     * Handles all POST requests.
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
