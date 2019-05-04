package com.careeroffice.servlet;

import com.careeroffice.model.User;
import com.careeroffice.model.UserCompany;
import com.careeroffice.model.UserDepartment;
import com.careeroffice.service.*;
import com.careeroffice.service.factory.ServiceEnum;
import com.careeroffice.service.factory.ServiceFactory;
import org.jasypt.util.password.StrongPasswordEncryptor;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet({"/create_user"})
public class SuperAdminCreateUserServlet extends HttpServlet {

    private static final UserService userService = (UserService) ServiceFactory.getService(ServiceEnum.UserService);
    private static final UserCompanyService userCompanyService = (UserCompanyService) ServiceFactory.getService(ServiceEnum.UserCompanyService);
    private static final UserDepartmentService userDepartmentService = (UserDepartmentService) ServiceFactory.getService(ServiceEnum.UserDepartmentService);

    private StrongPasswordEncryptor encryptor = new StrongPasswordEncryptor();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RoleService roleService = (RoleService) ServiceFactory.getService(ServiceEnum.RoleService);
        CompanyService companyService = (CompanyService) ServiceFactory.getService(ServiceEnum.CompanyService);
        DepartmentService departmentService = (DepartmentService) ServiceFactory.getService(ServiceEnum.DepartmentService);

        request.setAttribute("roles", roleService.findAll());
        request.setAttribute("companies", companyService.findAll());
        request.setAttribute("departments", departmentService.findAll());

        request.getRequestDispatcher("WEB-INF/views/super_admin/create_user.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO: Validate submission

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");
        String phoneNumber = request.getParameter("phone_number");
        String email = request.getParameter("email");
        String roleId = request.getParameter("role");
        String companyId = request.getParameter("company");
        String departmentId = request.getParameter("department");

        User user = new User(
                username,
                encryptor.encryptPassword(password),
                firstName,
                lastName,
                phoneNumber,
                email,
                roleId
        );

        userService.save(user);

        if (companyId != null && !companyId.equals("nothing")) {
            UserCompany userCompany = new UserCompany(username, companyId);
            userCompanyService.save(userCompany);
        }

        if (departmentId != null && !departmentId.equals("nothing")) {
            UserDepartment userDepartment = new UserDepartment(username, departmentId);
            userDepartmentService.save(userDepartment);
        }

        response.sendRedirect("view_user?id=" + username);
    }
}
