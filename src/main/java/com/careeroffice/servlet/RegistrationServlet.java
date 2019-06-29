package com.careeroffice.servlet;

import com.careeroffice.model.Company;
import com.careeroffice.model.Department;
import com.careeroffice.service.CompanyService;
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
import java.util.ArrayList;
import java.util.List;

/**
 * Handles student requests and responses.
 */
@WebServlet({"/RegistrationServlet", "/registration"})
public class RegistrationServlet extends HttpServlet {

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
        CompanyService companyService = (CompanyService) ServiceFactory.getService(ServiceEnum.CompanyService);
        DepartmentService departmentService = (DepartmentService) ServiceFactory.getService(ServiceEnum.DepartmentService);

        request.setAttribute("companies", companyService.findAll());
        request.setAttribute("departments", departmentService.findAll());

        request.getRequestDispatcher("WEB-INF/views/registration.jsp").forward(request, response);
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
        String companyId    = request.getParameter("company");
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
            request.getRequestDispatcher("WEB-INF/views/registration.jsp").forward(request, response);
            return;
        }

        if (role.equals("u_student") || role.equals("p_student"))
            registrationService.createUserStudent( username, encryptor.encryptPassword( password ), firstName, lastName, phone, email, role,  departmentId);
        else
            registrationService.createUserExternal( username, encryptor.encryptPassword( password ), firstName, lastName, phone, email, role, companyId );

        response.sendRedirect("login");
    }
}
