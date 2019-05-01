package com.careeroffice.servlet;

import com.careeroffice.service.CompanyService;
import com.careeroffice.service.DepartmentService;
import com.careeroffice.service.RoleService;
import com.careeroffice.service.UserService;
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

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("id");
        UserService userService = (UserService) ServiceFactory.getService(ServiceEnum.UserService);
        RoleService roleService = (RoleService) ServiceFactory.getService(ServiceEnum.RoleService);
        CompanyService companyService = (CompanyService) ServiceFactory.getService(ServiceEnum.CompanyService);
        DepartmentService departmentService = (DepartmentService) ServiceFactory.getService(ServiceEnum.DepartmentService);

        request.setAttribute("user", userService.findOne(username));
        request.setAttribute("roles", roleService.findAll());
        request.setAttribute("companies", companyService.findAll());
        request.setAttribute("departments", departmentService.findAll());

        request.getRequestDispatcher("WEB-INF/views/super_admin/edit_user.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
