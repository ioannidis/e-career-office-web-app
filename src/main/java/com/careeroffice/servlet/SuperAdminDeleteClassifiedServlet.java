package com.careeroffice.servlet;

import com.careeroffice.model.Classified;
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

@WebServlet({"/delete_classified"})
public class SuperAdminDeleteClassifiedServlet extends HttpServlet {

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

        int id = Integer.parseInt(request.getParameter("id"));
        Classified classified = classifiedService.findOne(id);

        request.setAttribute("classified", classified);

        request.getRequestDispatcher("WEB-INF/views/super_admin/delete_classified.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Classified classified = classifiedService.findOne(id);

        classifiedService.delete(classified.getId());

        response.sendRedirect("manage_classifieds");
    }
}
