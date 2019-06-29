package com.careeroffice.servlet;

import com.careeroffice.model.User;
import com.careeroffice.service.AuthService;
import com.careeroffice.service.UserCompanyService;
import com.careeroffice.service.UserDepartmentService;
import com.careeroffice.service.UserService;
import com.careeroffice.service.factory.ServiceEnum;
import com.careeroffice.service.factory.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet({"/delete_user"})
public class SuperAdminDeleteUserServlet extends HttpServlet {
    private static final UserService userService = (UserService) ServiceFactory.getService(ServiceEnum.UserService);
    private static final UserCompanyService userCompanyService = (UserCompanyService) ServiceFactory.getService(ServiceEnum.UserCompanyService);
    private static final UserDepartmentService userDepartmentService = (UserDepartmentService) ServiceFactory.getService(ServiceEnum.UserDepartmentService);

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

        request.setAttribute("user", userService.findOne(username));

        request.getRequestDispatcher("WEB-INF/views/super_admin/delete_user.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("id");

        User user = userService.findOne(username);

        if (user.getUserCompany() != null) {
            userCompanyService.delete(user.getUsername());
        }

        if (user.getUserDepartment() != null) {
            userDepartmentService.delete(user.getUsername());
        }

        // TODO: Delete associated tables or cascade existing in tables

        userService.delete(username);

        response.sendRedirect("manage_users");
    }
}
