package com.careeroffice.servlet;

import com.careeroffice.model.Keyword;
import com.careeroffice.service.AuthService;
import com.careeroffice.service.KeywordService;
import com.careeroffice.service.factory.ServiceEnum;
import com.careeroffice.service.factory.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet({"/edit_keyword"})
public class SuperAdminEditKeywordServlet extends HttpServlet {

    private static final KeywordService keywordService = (KeywordService) ServiceFactory.getService(ServiceEnum.KeywordService);

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
        Keyword keyword = keywordService.findOne(id);

        request.setAttribute("keyword", keyword);

        request.getRequestDispatcher("WEB-INF/views/super_admin/edit_keyword.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Keyword keyword = keywordService.findOne(id);

        String title = request.getParameter("title");
        String slug = request.getParameter("slug");

        keyword.setTitle(title);
        keyword.setSlug(slug);

        keywordService.update(keyword);

        response.sendRedirect("view_keyword?id=" + keyword.getId());
    }
}
