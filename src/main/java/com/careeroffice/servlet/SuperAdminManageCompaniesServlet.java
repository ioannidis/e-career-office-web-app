package com.careeroffice.servlet;

import com.careeroffice.service.AuthService;
import com.careeroffice.service.CompanyService;
import com.careeroffice.service.factory.ServiceEnum;
import com.careeroffice.service.factory.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet({"/manage_companies"})
public class SuperAdminManageCompaniesServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        AuthService authService = new AuthService(request.getSession());
        CompanyService companyService = (CompanyService) ServiceFactory.getService(ServiceEnum.CompanyService);

        request.setAttribute("user", authService.getUser());
        request.setAttribute("companies", companyService.findAll());

        request.getRequestDispatcher("WEB-INF/views/super_admin/manage_companies.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
