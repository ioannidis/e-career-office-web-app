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

@WebServlet({"/view_user"})
public class SuperAdminViewUserServlet extends HttpServlet {

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

        String username = request.getParameter("id");
        UserService userService = (UserService) ServiceFactory.getService(ServiceEnum.UserService);

        request.setAttribute("user", userService.findOne(username));

        request.getRequestDispatcher("WEB-INF/views/super_admin/view_user.jsp").forward(request, response);
    }
}
