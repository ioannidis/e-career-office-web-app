package com.careeroffice.servlet;

import com.careeroffice.service.*;
import com.careeroffice.service.factory.ServiceEnum;
import com.careeroffice.service.factory.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet({"/SuperAdminServlet", "/super_admin"})
public class SuperAdminServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        AuthService authService = new AuthService(request.getSession());
        UserService userService = (UserService) ServiceFactory.getService(ServiceEnum.UserService);
        RoleService roleService = (RoleService) ServiceFactory.getService(ServiceEnum.RoleService);
        CompanyService companyService = (CompanyService) ServiceFactory.getService(ServiceEnum.CompanyService);
        DepartmentService departmentService = (DepartmentService) ServiceFactory.getService(ServiceEnum.DepartmentService);
        CategoryService categoryService = (CategoryService) ServiceFactory.getService(ServiceEnum.CategoryService);

        request.setAttribute("user", authService.getUser());
        request.setAttribute("userCount", userService.count());
        request.setAttribute("roleCount", roleService.count());
        request.setAttribute("companyCount", companyService.count());
        request.setAttribute("departmentCount", departmentService.count());
        request.setAttribute("categoryCount", categoryService.count());

        request.getRequestDispatcher("WEB-INF/views/super_admin/index.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
