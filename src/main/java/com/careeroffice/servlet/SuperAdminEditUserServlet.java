package com.careeroffice.servlet;

import com.careeroffice.model.User;
import com.careeroffice.model.UserCompany;
import com.careeroffice.model.UserDepartment;
import com.careeroffice.service.*;
import com.careeroffice.service.factory.ServiceEnum;
import com.careeroffice.service.factory.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet({"/edit_user"})
public class SuperAdminEditUserServlet extends HttpServlet {
    private static final UserService userService = (UserService) ServiceFactory.getService(ServiceEnum.UserService);
    private static final CompanyService companyService = (CompanyService) ServiceFactory.getService(ServiceEnum.CompanyService);
    private static final DepartmentService departmentService = (DepartmentService) ServiceFactory.getService(ServiceEnum.DepartmentService);

    private static final UserCompanyService userCompanyService = (UserCompanyService) ServiceFactory.getService(ServiceEnum.UserCompanyService);
    private static final UserDepartmentService userDepartmentService = (UserDepartmentService) ServiceFactory.getService(ServiceEnum.UserDepartmentService);

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("id");

        request.setAttribute("user", userService.findOne(username));
        request.setAttribute("companies", companyService.findAll());
        request.setAttribute("departments", departmentService.findAll());

        request.getRequestDispatcher("WEB-INF/views/super_admin/edit_user.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("id");
        User user = userService.findOne(username);

        // TODO: Check duplicate username/email and validate submission

        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");
        String phoneNumber = request.getParameter("phone_number");
        String email = request.getParameter("email");
        String companyId = request.getParameter("company");
        String departmentId = request.getParameter("department");

        user.setName(firstName);
        user.setSurname(lastName);
        user.setPhoneNumber(phoneNumber);
        user.setEmail(email);
        userService.update(user);

        if (companyId != null && !companyId.equals("nothing")) {
            if (user.getUserCompany() == null) {
                userCompanyService.save(new UserCompany(user.getUsername(), companyId));
            } else {
                user.getUserCompany().setCompanyId(companyId);
                userCompanyService.update(user.getUserCompany());
            }
        } else {
            userCompanyService.delete(user.getUsername());
        }

        if (departmentId != null && !departmentId.equals("nothing")) {
            if (user.getUserDepartment() == null) {
                userDepartmentService.save(new UserDepartment(user.getUsername(), departmentId));
            } else {
                user.getUserDepartment().setDepartmentId(departmentId);
                userDepartmentService.update(user.getUserDepartment());
            }

        } else {
            userDepartmentService.delete(user.getUsername());
        }

        response.sendRedirect("view_user?id=" + user.getUsername());
    }
}
