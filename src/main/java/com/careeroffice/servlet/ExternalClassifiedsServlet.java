package com.careeroffice.servlet;

import com.careeroffice.model.Category;
import com.careeroffice.model.Classified;
import com.careeroffice.model.Keyword;
import com.careeroffice.service.AuthService;
import com.careeroffice.service.CategoryService;
import com.careeroffice.service.ClassifiedService;
import com.careeroffice.service.KeywordService;
import com.careeroffice.service.factory.ServiceEnum;
import com.careeroffice.service.factory.ServiceFactory;
import com.careeroffice.util.UrlUtil;
import com.google.protobuf.Internal;
import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@WebServlet({"/ExternalClassifiedsServlet", "/externalclassifieds"})
public class ExternalClassifiedsServlet extends HttpServlet {
    /**
     * Java related serial version UID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Handles all GET requests.
     */
    protected void doGet( HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        AuthService authService = new AuthService(request.getSession());
        ClassifiedService classifiedService = (ClassifiedService) ServiceFactory.getService( ServiceEnum.ClassifiedService );
        CategoryService categoryService = (CategoryService) ServiceFactory.getService( ServiceEnum.CategoryService );
        KeywordService keywordService = (KeywordService) ServiceFactory.getService( ServiceEnum.KeywordService );

        if (!authService.isLoggedIn()) {
            response.sendRedirect("login");
            return;
        }

        if (!authService.hasRole("external")) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        String action = UrlUtil.getParameterOrDefault(request, "action", "index");
        Integer id = UrlUtil.getParameterOrDefault(request, "id", -1);

        switch (action) {
            case "create": {
                request.setAttribute( "categories", categoryService.findAll() );
                request.setAttribute( "allKeywords", keywordService.findAll() );
                request.getRequestDispatcher("WEB-INF/views/classified/create.jsp").forward(request, response);
                break;
            }
            case "show": {
                Classified classified = classifiedService.findOne(id);

                System.out.println( keywordService.findByClassified(classified.getId())  );

                request.setAttribute( "classified", classified );
                request.setAttribute( "category", categoryService.findOne(classified.getCategoryId()) );
                request.setAttribute( "keywords", keywordService.findByClassified(classified.getId()) );
                request.getRequestDispatcher("WEB-INF/views/classified/show.jsp").forward(request, response);
                break;
            }
            case "edit": {
                Classified classified = classifiedService.findOne(id);
                List<Keyword> keywords = keywordService.findAll();
                Map<Integer, Keyword> selectedKeywords = keywordService.findByClassified(classified.getId()).stream()
                        .collect( Collectors.toMap( x -> x.getId(), x -> x ) );

                request.setAttribute( "classified", classified );
                request.setAttribute( "categories", categoryService.findAll() );
                request.setAttribute( "selectedKeywords", selectedKeywords );
                request.setAttribute( "allKeywords", keywords );
                request.getRequestDispatcher("WEB-INF/views/classified/edit.jsp").forward(request, response);
                break;
            }
            case "delete": {
                classifiedService.delete(id);
                response.sendRedirect("externalclassifieds");
                break;
            }
            default: {
                List<Classified> classifieds = classifiedService.findAllByCompany( "ibm" );

                Map<Integer, Category> categoryMap = categoryService.findAll().stream()
                        .collect( Collectors.toMap( Category::getId, x -> x ) );

                request.setAttribute( "classifieds", classifieds );
                request.setAttribute( "categories", categoryMap );
                request.getRequestDispatcher("WEB-INF/views/classified/index.jsp").forward(request, response);
                break;
            }
        }

        //request.getRequestDispatcher("WEB-INF/views/external/index.jsp").forward(request, response);
    }

    /**
     * Handles all POST requests.
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        AuthService authService = new AuthService(request.getSession());
        ClassifiedService classifiedService = (ClassifiedService) ServiceFactory.getService( ServiceEnum.ClassifiedService );

        if (!authService.isLoggedIn()) {
            response.sendRedirect("login");
            return;
        }

        if (!authService.hasRole("external")) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        String action = UrlUtil.getParameterOrDefault(request, "action", "index");
        Integer id = UrlUtil.getParameterOrDefault(request, "id", -1);

        switch (action) {
            case "save": {
                Classified classified = new Classified(
                        request.getParameter("title"),
                        request.getParameter("content"),
                        request.getParameter("companyId"),
                        Integer.parseInt(request.getParameter("categoryId"))
                );
                classifiedService.save(classified);

                //TODO: save keywords

                response.sendRedirect("externalclassifieds");
                break;
            }
            case "update": {
                Classified classified = classifiedService.findOne(id);

                classified.setTitle(UrlUtil.getParameterOrDefault(request, "title", classified.getTitle()));
                classified.setContent(UrlUtil.getParameterOrDefault(request, "content", classified.getContent()));
                classified.setCompanyId(UrlUtil.getParameterOrDefault(request, "companyId", classified.getCompanyId()));
                classified.setCategoryId(UrlUtil.getParameterOrDefault(request, "categoryId", classified.getCategoryId()));

                classifiedService.update(classified);

                //TODO: update keywords

                response.sendRedirect("externalclassifieds");
                break;
            }
            default: {
                doGet( request, response );
                break;
            }
        }

    }
}
