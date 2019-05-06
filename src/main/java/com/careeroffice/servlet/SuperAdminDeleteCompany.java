package com.careeroffice.servlet;

import com.careeroffice.model.Company;
import com.careeroffice.service.CompanyService;
import com.careeroffice.service.UserCompanyService;
import com.careeroffice.service.factory.ServiceEnum;
import com.careeroffice.service.factory.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet({"/delete_company"})
public class SuperAdminDeleteCompany extends HttpServlet {

    private CompanyService companyService = (CompanyService) ServiceFactory.getService(ServiceEnum.CompanyService);
    private static final UserCompanyService userCompanyService = (UserCompanyService) ServiceFactory.getService(ServiceEnum.UserCompanyService);

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String id = request.getParameter("id");
        Company company = companyService.findOne(id);

        request.setAttribute("company", company);

        request.getRequestDispatcher("WEB-INF/views/super_admin/delete_company.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String id = request.getParameter("id");
        Company company = companyService.findOne(id);

        userCompanyService.findAll().forEach(userCompany -> {
            if (userCompany.getCompanyId().equals(company.getId())) {
                userCompanyService.delete(userCompany.getUsername());
            }
        });

        companyService.delete(company.getId());

        response.sendRedirect("manage_companies");
    }
}