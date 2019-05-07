package com.careeroffice.servlet;

import com.careeroffice.model.*;
import com.careeroffice.model.decorator.ClassifiedKeywordDecorator;
import com.careeroffice.service.*;
import com.careeroffice.service.factory.ServiceEnum;
import com.careeroffice.service.factory.ServiceFactory;
import com.careeroffice.service.pivot.KeywordClassifiedPivotService;
import com.careeroffice.service.CvService;
import com.careeroffice.service.student.KeywordCvService;
import com.careeroffice.util.UrlUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.stream.Collectors;

@WebServlet({"/AdminClassifiedsServlet", "/adminclassifieds"})
public class AdminClassifiedsServlet extends HttpServlet {
    /**
     * Java related serial version UID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Handles all GET requests.
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {




        AuthService authService = new AuthService(request.getSession());
        ClassifiedService classifiedService = (ClassifiedService) ServiceFactory.getService( ServiceEnum.ClassifiedService );
        CategoryService categoryService = (CategoryService) ServiceFactory.getService( ServiceEnum.CategoryService );
        KeywordService keywordService = new KeywordService();
        KeywordClassifiedPivotService keywordClassifiedPivotService = new KeywordClassifiedPivotService();
        UserService userService = new UserService();


        if (!authService.isLoggedIn()) {
            response.sendRedirect("login");
            return;
        }

        if (!authService.hasRole("admin")) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        String name = UrlUtil.getParameterOrDefault(request, "name", "a Student");

        List<Classified> classifieds = classifiedService.findAll();

        List<ClassifiedKeywordDecorator> classifiedList = new ArrayList<>();

        String action = UrlUtil.getParameterOrDefault(request, "action", "index");
        Integer id = UrlUtil.getParameterOrDefault(request, "id", -1);

        switch (action) {
            case "show": {
                Classified classified = classifiedService.findOne(id);

                System.out.println(keywordClassifiedPivotService.findByClassified(classified.getId()));

                request.setAttribute("classified", classified);
                request.setAttribute("category", categoryService.findOne(classified.getCategoryId()));
                request.setAttribute("keywords", keywordClassifiedPivotService.findByClassified(classified.getId()));
                request.getRequestDispatcher("WEB-INF/views/admin/show_classified.jsp").forward(request, response);
                break;
            }
            case "edit": {
                Classified classified = classifiedService.findOne(id);
                List<Keyword> keywords = keywordService.findAll();
                Map<Integer, Keyword> selectedKeywords = keywordClassifiedPivotService.findByClassified(classified.getId()).stream()
                        .collect(Collectors.toMap(x -> x.getId(), x -> x));

                request.setAttribute("classified", classified);
                request.setAttribute("categories", categoryService.findAll());
                request.setAttribute("selectedKeywords", selectedKeywords);
                request.setAttribute("allKeywords", keywords);
                request.getRequestDispatcher("WEB-INF/views/admin/edit_classified.jsp").forward(request, response);
                break;
            }
            case "delete": {
                keywordClassifiedPivotService.deleteByClassifiedId(id);
                classifiedService.delete(id);

                response.sendRedirect("adminclassifieds");
                break;
            }
            default: {
                for (Classified classified : classifieds
                ) {
                    List<Keyword> keywordList = keywordClassifiedPivotService.findByClassified(classified.getId());
                    ClassifiedKeywordDecorator classifiedKeywordDecorator = new ClassifiedKeywordDecorator(classified);
                    classifiedKeywordDecorator.setKeywords(keywordList);
                    classifiedList.add(classifiedKeywordDecorator);
                }

                Map<Integer, Category> categoryMap = categoryService.findAll().stream()
                        .collect( Collectors.toMap( Category::getId, x -> x ) );
                request.setAttribute( "classifieds", classifiedList );
                request.setAttribute( "categories", categoryMap );
                request.setAttribute("name", name);
                request.getRequestDispatcher("WEB-INF/views/admin/classifieds.jsp").forward(request, response);
            }
        }


    }

    /**
     * Handles all POST requests.
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        AuthService authService = new AuthService(request.getSession());
        ClassifiedService classifiedService = (ClassifiedService) ServiceFactory.getService(ServiceEnum.ClassifiedService);
        KeywordClassifiedPivotService keywordClassifiedPivotService = (KeywordClassifiedPivotService) ServiceFactory.getService(ServiceEnum.KeywordClassifiedPivotService);


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
                Classified classified = new Classified(
                        request.getParameter("title"),
                        request.getParameter("content"),
                        request.getParameter("companyId"),
                        Integer.parseInt(request.getParameter("categoryId"))
                );

                classified = classifiedService.save(classified);

                String[] keywordArray = request.getParameterValues("keywordIds");

                for ( String x: keywordArray) {
                    keywordClassifiedPivotService.save(new KeywordClassifiedPivot(Integer.valueOf(x), classified.getId()));
                }

                response.sendRedirect("adminclassifieds");
                break;
            }
            case "update": {
                Classified classified = classifiedService.findOne(id);

                classified.setTitle(UrlUtil.getParameterOrDefault(request, "title", classified.getTitle()));
                classified.setContent(UrlUtil.getParameterOrDefault(request, "content", classified.getContent()));
                classified.setCompanyId(UrlUtil.getParameterOrDefault(request, "companyId", classified.getCompanyId()));
                classified.setCategoryId(UrlUtil.getParameterOrDefault(request, "categoryId", classified.getCategoryId()));

                classifiedService.update(classified);

                keywordClassifiedPivotService.deleteByClassifiedId( classified.getId() );

                String[] keywordArray = request.getParameterValues("keywordIds");
                for ( String x: keywordArray) {
                    keywordClassifiedPivotService.save(new KeywordClassifiedPivot(Integer.valueOf(x), classified.getId()));
                }

                response.sendRedirect("adminclassifieds");
                break;
            }
            default: {
                doGet(request, response);
                break;
            }
        }


    }
}
