package com.careeroffice.servlet;

import com.careeroffice.model.User;
import com.careeroffice.service.AuthService;
import com.careeroffice.service.LoginService;
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
@WebServlet({"/LoginServlet", "/login"})
public class LoginServlet extends HttpServlet {

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

        HttpSession session = request.getSession();
        AuthService auth = new AuthService(session);

        if (auth.isLoggedIn()) {
            User user = (User) session.getAttribute("user");
            response.sendRedirect(user.getRoleId());
        } else {
            request.getRequestDispatcher("WEB-INF/views/login.jsp").forward(request, response);
        }

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
