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

@WebServlet({"/edit_company"})
public class SuperAdminEditCompanyServlet extends HttpServlet {

    private CompanyService companyService = (CompanyService) ServiceFactory.getService(ServiceEnum.CompanyService);

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String id = request.getParameter("id");
        Company company = companyService.findOne(id);

        request.setAttribute("company", company);
        request.getRequestDispatcher("WEB-INF/views/super_admin/edit_company.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String id = request.getParameter("id");
        Company company = companyService.findOne(id);

        String title = request.getParameter("title");
        String address = request.getParameter("address");
        String phoneNumber = request.getParameter("phone_number");
        String email = request.getParameter("email");
        String website = request.getParameter("website");

        company.setTitle(title);
        company.setAddress(address);
        company.setPhoneNumber(phoneNumber);
        company.setEmail(email);
        company.setWebsite(website);

        companyService.update(company);

        response.sendRedirect("view_company?id=" + company.getId());
    }
}