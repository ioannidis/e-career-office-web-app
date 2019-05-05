package com.careeroffice.servlet;

import com.careeroffice.model.*;
import com.careeroffice.service.*;
import com.careeroffice.service.factory.ServiceEnum;
import com.careeroffice.service.factory.ServiceFactory;
import com.careeroffice.service.pivot.KeywordClassifiedPivotService;
import com.careeroffice.service.student.CvService;
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
        KeywordCvService keywordCvService = new KeywordCvService();
        KeywordService keywordService = new KeywordService();
        CvService cvService = new CvService();
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
        List<String> keywords = new ArrayList<>();


        if (!(name.equals("a Student"))) {
            List<Classified> deleteClassifieds = new ArrayList<>();
                for (Classified classified : classifieds
                ) {
                    try {
                        Cv studentCv = cvService.findOne(name);
                        List<Keyword> studentKeywords = keywordCvService.findByCv(studentCv.getId());
                        List<Keyword> keywordList = keywordClassifiedPivotService.findByClassified(classified.getId());
                        boolean found = false;
                        for (Keyword keyword : studentKeywords
                        ) {
                            for (Keyword clKeyword : keywordList
                            ) {
                                if (keyword.getSlug().equals(clKeyword.getSlug())) {
                                    found = true;
                                    break;
                                }
                            }
                        }
                        if (!found) {
                            deleteClassifieds.add(classified);
                        }
                    } catch (NullPointerException e) {
                        deleteClassifieds.add(classified);
                    }
                }
            if (deleteClassifieds.size() != 0) {
                for (Classified classified : deleteClassifieds
                ) {
                    classifieds.remove(classified);
                }
            }


        }
        for (Classified classified:classifieds
        ) {
            StringJoiner joiner = new StringJoiner(",");
            try {
                List<Keyword> keywordCv = keywordClassifiedPivotService.findByClassified(classified.getId());
                for (Keyword keyword:keywordCv
                ) {
                    joiner.add(keyword.getTitle());
                }
                keywords.add(joiner.toString());
            }
            catch (NullPointerException e){
                keywords.add("None");
            }
        }


        Map<Integer, Category> categoryMap = categoryService.findAll().stream()
                .collect( Collectors.toMap( Category::getId, x -> x ) );
        request.setAttribute( "classifieds", classifieds );
        request.setAttribute( "categories", categoryMap );
        request.setAttribute("name", name);
        request.setAttribute("keywords", keywords);
        request.getRequestDispatcher("WEB-INF/views/admin/classifieds.jsp").forward(request, response);

    }

    /**
     * Handles all POST requests.
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request,response);
    }
}
