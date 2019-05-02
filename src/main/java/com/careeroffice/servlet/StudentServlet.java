package com.careeroffice.servlet;

import com.careeroffice.model.User;
import com.careeroffice.service.*;
import com.careeroffice.service.factory.ServiceEnum;
import com.careeroffice.service.factory.ServiceFactory;
import org.jasypt.util.password.StrongPasswordEncryptor;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


/**
 * Handles login requests and responses.
 */
@WebServlet({"/StudentServlet", "/student", "/p_student", "/u_student"})
public class StudentServlet extends HttpServlet {

    /**
     * Java related serial version UID.
     */
    private static final long serialVersionUID = 1L;

    private StrongPasswordEncryptor encryptor = new StrongPasswordEncryptor();

    /**
     * Handles all GET requests.
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        AuthService authService = new AuthService(request.getSession());

        request.setAttribute("user", authService.getUser());

        request.getRequestDispatcher("WEB-INF/views/student/index.jsp").forward(request, response);

    }

    /**
     * Handles all POST requests.
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        LoginService login = (LoginService) ServiceFactory.getService(ServiceEnum.LoginService);
        HttpSession session = request.getSession();

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = login.auth(username);

        if (user != null && encryptor.checkPassword(password, user.getPassword())) {
            session.setAttribute("loggedIn", true);
            session.setAttribute("user", user);

            response.sendRedirect(user.getRoleId());
        } else {
            response.sendRedirect("login");
        }

    }
}
