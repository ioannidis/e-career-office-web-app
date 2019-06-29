package com.careeroffice.servlet;

import com.careeroffice.service.AuthService;
import com.careeroffice.service.ClassifiedService;
import com.careeroffice.service.factory.ServiceEnum;
import com.careeroffice.service.factory.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet({"/manage_classifieds"})
public class SuperAdminManageClassifiedsServlet extends HttpServlet {

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

        request.setAttribute("classifieds", classifiedService.findAll());
        request.setAttribute("classifiedCount", classifiedService.count());

        request.getRequestDispatcher("WEB-INF/views/super_admin/manage_classifieds.jsp").forward(request, response);
    }
}