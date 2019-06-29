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

    private static final UserService userService = (UserService) ServiceFactory.getService(ServiceEnum.UserService);
    private static final RoleService roleService = (RoleService) ServiceFactory.getService(ServiceEnum.RoleService);
    private static final CompanyService companyService = (CompanyService) ServiceFactory.getService(ServiceEnum.CompanyService);
    private static final DepartmentService departmentService = (DepartmentService) ServiceFactory.getService(ServiceEnum.DepartmentService);
    private static final CategoryService categoryService = (CategoryService) ServiceFactory.getService(ServiceEnum.CategoryService);
    private static final KeywordService keywordService = (KeywordService) ServiceFactory.getService(ServiceEnum.KeywordService);
    private static final ClassifiedService classifiedService = (ClassifiedService) ServiceFactory.getService(ServiceEnum.ClassifiedService);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        AuthService authService = new AuthService(request.getSession());
        if (!authService.isLoggedIn()) {
            response.sendRedirect("login");
            return;
        }

        if (!authService.hasRole("super_admin")) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        request.setAttribute("user", authService.getUser());
        request.setAttribute("userCount", userService.count());
        request.setAttribute("roleCount", roleService.count());
        request.setAttribute("companyCount", companyService.count());
        request.setAttribute("departmentCount", departmentService.count());
        request.setAttribute("categoryCount", categoryService.count());
        request.setAttribute("keywordCount", keywordService.count());
        request.setAttribute("classifiedCount", classifiedService.count());

        request.getRequestDispatcher("WEB-INF/views/super_admin/index.jsp").forward(request, response);
    }
}
