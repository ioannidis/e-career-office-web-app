package com.careeroffice.servlet;

import com.careeroffice.model.Category;
import com.careeroffice.model.Classified;
import com.careeroffice.model.Skills;
import com.careeroffice.service.AuthService;
import com.careeroffice.service.CategoryService;
import com.careeroffice.service.ClassifiedService;
import com.careeroffice.service.factory.ServiceEnum;
import com.careeroffice.service.factory.ServiceFactory;
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

        String name = UrlUtil.getParameterOrDefault(request, "name", "a Student");
        String studentSkills = UrlUtil.getParameterOrDefault(request, "studentSkills", "All");


        AuthService authService = new AuthService(request.getSession());
        ClassifiedService classifiedService = (ClassifiedService) ServiceFactory.getService( ServiceEnum.ClassifiedService );
        CategoryService categoryService = (CategoryService) ServiceFactory.getService( ServiceEnum.CategoryService );

        if (!authService.isLoggedIn()) {
            response.sendRedirect("login");
            return;
        }

        if (!authService.hasRole("admin")) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }


        List<Classified> classifieds = classifiedService.findAll();
        for (Classified job:classifieds
             ) {
            Skills skills = new Skills(String.valueOf(job.getId()),classifiedService.findClassifiedSkills(job.getId()));
            job.setSkills(skills);
        }


        if (!(studentSkills.equals("All"))) {
            if (!(studentSkills.equals("None"))) {
                List<Classified> deleteClassifieds = new ArrayList<>();
                for (Classified job : classifieds
                ) {
                    String skills = job.getSkills().getSkills();

                    String[] all;
                    if (!(skills.contains(","))) {
                        all = new String[1];
                        all[0] = skills;
                    }
                    else{
                        all = skills.split(",");
                    }



                    String[] studentAll = studentSkills.split(",");
                    boolean found = false;
                    for (String classifiedSkill : all
                    ) {
                        for (String skill : studentAll
                        ) {
                            if (classifiedSkill.equals(skill)) {
                                found = true;
                            }
                        }
                    }
                    if (!found) {
                        deleteClassifieds.add(job);
                    }


                }

                if (deleteClassifieds.size() != 0) {
                    for (Classified job : deleteClassifieds
                    ) {
                        classifieds.remove(job);
                    }
                }
            } else {
                classifieds.clear();
            }
        }

        Map<Integer, Category> categoryMap = categoryService.findAll().stream()
                .collect( Collectors.toMap( Category::getId, x -> x ) );
        request.setAttribute( "classifieds", classifieds );
        request.setAttribute( "categories", categoryMap );
        request.setAttribute("name", name);
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
