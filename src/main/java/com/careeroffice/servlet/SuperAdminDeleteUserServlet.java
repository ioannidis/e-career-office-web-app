package com.careeroffice.servlet;

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

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("id");

        request.setAttribute("user", userService.findOne(username));

        request.getRequestDispatcher("WEB-INF/views/super_admin/delete_user.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("id");
        userService.delete(username);
        response.sendRedirect("/manage_users");
    }
}
