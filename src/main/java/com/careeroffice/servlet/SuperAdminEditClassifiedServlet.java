package com.careeroffice.servlet;

import com.careeroffice.model.Classified;
import com.careeroffice.model.Keyword;
import com.careeroffice.model.KeywordClassifiedPivot;
import com.careeroffice.service.*;
import com.careeroffice.service.factory.ServiceEnum;
import com.careeroffice.service.factory.ServiceFactory;
import com.careeroffice.service.pivot.KeywordClassifiedPivotService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet({"/edit_classified"})
public class SuperAdminEditClassifiedServlet extends HttpServlet {

    private static final ClassifiedService classifiedService = (ClassifiedService) ServiceFactory.getService(ServiceEnum.ClassifiedService);
    private static final CompanyService companyService = (CompanyService) ServiceFactory.getService(ServiceEnum.CompanyService);
    private static final CategoryService categoryService = (CategoryService) ServiceFactory.getService(ServiceEnum.CategoryService);
    private static final KeywordService keywordService = (KeywordService) ServiceFactory.getService(ServiceEnum.KeywordService);
    private static final KeywordClassifiedPivotService keywordClassifiedPivotService = (KeywordClassifiedPivotService) ServiceFactory.getService(ServiceEnum.KeywordClassifiedPivotService);

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
        request.setAttribute("companies", companyService.findAll());
        request.setAttribute("categories", categoryService.findAll());
        request.setAttribute("keywords", keywordService.findAll());

        List<Keyword> selectedKeywords = new ArrayList<>();
        classified.getKeywordClassifiedPivotList().forEach(pivot -> selectedKeywords.add(pivot.getKeyword()));

        request.setAttribute("selectedKeywords", selectedKeywords);

        request.getRequestDispatcher("WEB-INF/views/super_admin/edit_classified.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Classified classified = classifiedService.findOne(id);

        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String companyId = request.getParameter("company");
        int categoryId = Integer.parseInt(request.getParameter("category"));
        String[] keywordIds = request.getParameterValues("keywords");

        classified.setTitle(title);
        classified.setContent(content);
        classified.setCompanyId(companyId);
        classified.setCategoryId(categoryId);

        classifiedService.update(classified);

        keywordClassifiedPivotService.deleteByClassifiedId(classified.getId());

        if (keywordIds != null) {
            for (String keywordId : keywordIds) {
                int currentId = Integer.parseInt(keywordId);

                KeywordClassifiedPivot newKeyword = new KeywordClassifiedPivot(currentId, classified.getId());
                keywordClassifiedPivotService.save(newKeyword);
            }
        }

        response.sendRedirect("view_classified?id=" + classified.getId());
    }
}