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

@WebServlet({"/create_company"})
public class SuperAdminCreateCompany extends HttpServlet {

    private CompanyService companyService = (CompanyService) ServiceFactory.getService(ServiceEnum.CompanyService);

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/views/super_admin/create_company.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("company_id");
        String title = request.getParameter("title");
        String address = request.getParameter("address");
        String phoneNumber = request.getParameter("phone_number");
        String email = request.getParameter("email");
        String website = request.getParameter("website");

        Company company = new Company(id, title, address, phoneNumber, email, website);

        companyService.save(company);

        response.sendRedirect("view_company?id=" + company.getId());
    }
}