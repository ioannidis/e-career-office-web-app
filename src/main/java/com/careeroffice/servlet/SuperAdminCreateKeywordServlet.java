package com.careeroffice.servlet;

import com.careeroffice.model.Keyword;
import com.careeroffice.service.KeywordService;
import com.careeroffice.service.factory.ServiceEnum;
import com.careeroffice.service.factory.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet({"/create_keyword"})
public class SuperAdminCreateKeywordServlet extends HttpServlet {

    private static final KeywordService keywordService = (KeywordService) ServiceFactory.getService(ServiceEnum.KeywordService);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/views/super_admin/create_keyword.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("title");
        String slug = request.getParameter("slug");

        Keyword keyword = new Keyword(title, slug);
        keyword = keywordService.save(keyword);

        response.sendRedirect("view_keyword?id=" + keyword.getId());
    }
}
