package com.careeroffice.servlet;

import com.careeroffice.model.Company;
import com.careeroffice.service.CompanyService;
import com.careeroffice.service.factory.ServiceEnum;
import com.careeroffice.service.factory.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet({"/view_company"})
public class SuperAdminViewCompany extends HttpServlet {

    private CompanyService companyService = (CompanyService) ServiceFactory.getService(ServiceEnum.CompanyService);

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String id = request.getParameter("id");
        Company company = companyService.findOne(id);

        request.setAttribute("company", company);

        request.getRequestDispatcher("WEB-INF/views/super_admin/view_company.jsp").forward(request, response);
    }
}
