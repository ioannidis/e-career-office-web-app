package com.careeroffice.servlet;

import com.careeroffice.service.AuthService;
import com.careeroffice.service.RoleService;
import com.careeroffice.service.factory.ServiceEnum;
import com.careeroffice.service.factory.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet({"/manage_roles"})
public class SuperAdminManageRolesServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        AuthService authService = new AuthService(request.getSession());
        if (!authService.isLoggedIn()) {
            response.sendRedirect("login");
            return;
        }

        if (!authService.hasRole("super_admin")) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        RoleService roleService = (RoleService) ServiceFactory.getService(ServiceEnum.RoleService);

        request.setAttribute("user", authService.getUser());
        request.setAttribute("roles", roleService.findAll());
        request.setAttribute("roleCount", roleService.count());

        request.getRequestDispatcher("WEB-INF/views/super_admin/manage_roles.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
