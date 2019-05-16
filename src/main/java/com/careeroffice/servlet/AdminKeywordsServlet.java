package com.careeroffice.servlet;


import com.careeroffice.model.Keyword;
import com.careeroffice.service.AuthService;
import com.careeroffice.service.KeywordService;
import com.careeroffice.service.factory.ServiceEnum;
import com.careeroffice.service.factory.ServiceFactory;
import com.careeroffice.util.UrlUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@WebServlet({"/AdminKeywrodsServlet", "/adminkeywords"})
public class AdminKeywordsServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * Handles all GET requests.
     */
    private static final KeywordService keywordService = (KeywordService) ServiceFactory.getService(ServiceEnum.KeywordService);

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        AuthService authService = new AuthService(request.getSession());

        if (!authService.isLoggedIn()) {
            response.sendRedirect("login");
            return;
        }

        if (!authService.hasRole("admin")) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        String action = UrlUtil.getParameterOrDefault(request, "action", "index");
        Integer id = UrlUtil.getParameterOrDefault(request, "id", -1);

        switch (action) {
            case "show": {
                Keyword keyword = keywordService.findOne(id);
                request.setAttribute("keyword", keyword);
                request.getRequestDispatcher("WEB-INF/views/admin/show_keywords.jsp").forward(request, response);
                break;
            }
            case "create": {
                request.getRequestDispatcher("WEB-INF/views/admin/create_keywords.jsp").forward(request, response);
                break;
            }
            case "edit": {
                Keyword keyword = keywordService.findOne(id);
                request.setAttribute("keyword", keyword);
                request.getRequestDispatcher("WEB-INF/views/admin/edit_keywords.jsp").forward(request, response);
                break;
            }
            case "delete": {
                keywordService.delete(id);
                response.sendRedirect("adminkeywords");
                break;
            }

            default: {
                List<Keyword> keywords = keywordService.findAll();
                request.setAttribute("keywords", keywords);
                request.getRequestDispatcher("WEB-INF/views/admin/keywords.jsp").forward(request, response);
            }
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        AuthService authService = new AuthService(request.getSession());

        if (!authService.isLoggedIn()) {
            response.sendRedirect("login");
            return;
        }

        if (!authService.hasRole("admin")) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        String action = UrlUtil.getParameterOrDefault(request, "action", "index");
        Integer id = UrlUtil.getParameterOrDefault(request, "id", -1);

        switch (action) {
            case "save": {
                String title = request.getParameter("title");
                String slug = request.getParameter("slug");

                Keyword keyword = new Keyword(title, slug);
                keyword = keywordService.save(keyword);
                response.sendRedirect("adminkeywords");
                break;
            }
            case "update": {
                Keyword keyword = keywordService.findOne(id);

                String title = request.getParameter("title");
                String slug = request.getParameter("slug");

                keyword.setTitle(title);
                keyword.setSlug(slug);

                keywordService.update(keyword);

                response.sendRedirect("adminkeywords");
                break;
            }

            default: {
                doGet(request, response);
                break;
            }
        }
    }
}

