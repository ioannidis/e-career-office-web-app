package com.careeroffice.servlet;

import com.careeroffice.model.User;
import com.careeroffice.service.AuthService;
import com.careeroffice.service.RegistrationService;
import com.careeroffice.service.UserService;
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



@WebServlet({"/StudentInformationCrud", "/student_crud"})
public class StudentInformationCrud extends HttpServlet {

    private StrongPasswordEncryptor encryptor = new StrongPasswordEncryptor();
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        AuthService authService = new AuthService(request.getSession());
        User user = authService.getUser();
        request.setAttribute("user", user);
        request.getRequestDispatcher("WEB-INF/views/student/student_crud.jsp").forward(request, response);

    }

    /**
     * Handles all POST requests.
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserService userService = (UserService) ServiceFactory.getService(ServiceEnum.UserService);
        AuthService authService = new AuthService(request.getSession());
        HttpSession session = request.getSession();

        User user = authService.getUser();

        String password     = request.getParameter("password");
        String firstName    = request.getParameter("firstname");
        String lastName     = request.getParameter("lastname");
        String email        = request.getParameter("email");
        String phone        = request.getParameter("phone");
        String role         = request.getParameter("role");

        User newUser = new User(user.getUsername(), encryptor.encryptPassword(password), firstName, lastName,
                phone, email, role);

        userService.update(user);
        session.removeAttribute("user");
        session.setAttribute("user", newUser);
        request.getRequestDispatcher("WEB-INF/views/student/index.jsp").forward(request, response);
    }
}
