package com.careeroffice.servlet;

import com.careeroffice.service.AuthService;
import com.careeroffice.service.UserService;
import com.careeroffice.service.factory.ServiceEnum;
import com.careeroffice.service.factory.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet({"/SuperAdminManageUsersServlet", "/manage_users"})
public class SuperAdminManageUsersServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        AuthService authService = new AuthService(request.getSession());
        UserService userService = (UserService) ServiceFactory.getService(ServiceEnum.UserService);

        request.setAttribute("user", authService.getUser());
        request.setAttribute("users", userService.findAll());
        request.setAttribute("userCount", userService.count());

        request.getRequestDispatcher("WEB-INF/views/super_admin/manage_users.jsp").forward(request, response);
    }
}
