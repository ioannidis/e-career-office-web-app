package com.careeroffice.servlet;

import com.careeroffice.model.Department;
import com.careeroffice.service.AuthService;
import com.careeroffice.service.DepartmentService;
import com.careeroffice.service.RegistrationService;
import com.careeroffice.service.factory.ServiceEnum;
import com.careeroffice.service.factory.ServiceFactory;
import org.jasypt.util.password.StrongPasswordEncryptor;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet({"/AdminRegisterServlet", "/adminregister"})
public class AdminRegisterServlet extends HttpServlet {
    /**
     * Java related serial version UID.
     */
    private static final long serialVersionUID = 1L;

    private StrongPasswordEncryptor encryptor = new StrongPasswordEncryptor();

    /**
     * Handles all GET requests.
     */
    @Override
    protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        DepartmentService departmentService = (DepartmentService) ServiceFactory.getService( ServiceEnum.DepartmentService );

        request.setAttribute("departments", departmentService.findAll());
        request.getRequestDispatcher("WEB-INF/views/admin/register.jsp").forward(request, response);
    }

    /**
     * Handles all POST requests.
     */
    @Override
    protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        RegistrationService registrationService = (RegistrationService) ServiceFactory.getService(ServiceEnum.RegistrationService);

        String username     = request.getParameter("username");
        String password     = request.getParameter("password");
        String firstName    = request.getParameter("firstname");
        String lastName     = request.getParameter("lastname");
        String email        = request.getParameter("email");
        String phone        = request.getParameter("phone");
        String role         = request.getParameter("role");
        String departmentId = request.getParameter("department");

        boolean hasError = false;
        if (registrationService.searchUserBy(username, "username")) {
            hasError = true;
            request.setAttribute("usernameError", "Username exists!");
        }

        if (registrationService.searchUserBy(email, "email")) {
            hasError = true;
            request.setAttribute("emailError", "Email exists!");
        }

        if (hasError) {
            request.getRequestDispatcher("WEB-INF/views/admin/register.jsp").forward(request, response);
            return;
        }

        registrationService.createUserStudent( username, encryptor.encryptPassword( password ), firstName, lastName, phone, email, role, departmentId);

        response.sendRedirect("adminstudents");
    }
}
