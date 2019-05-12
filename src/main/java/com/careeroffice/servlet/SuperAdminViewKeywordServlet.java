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

@WebServlet({"/view_keyword"})
public class SuperAdminViewKeywordServlet extends HttpServlet {

    private static final KeywordService keywordService = (KeywordService) ServiceFactory.getService(ServiceEnum.KeywordService);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        Keyword keyword = keywordService.findOne(id);

        request.setAttribute("keyword", keyword);

        request.getRequestDispatcher("WEB-INF/views/super_admin/view_keyword.jsp").forward(request, response);
    }
}
