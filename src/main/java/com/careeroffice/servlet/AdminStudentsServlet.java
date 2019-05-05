package com.careeroffice.servlet;

import com.careeroffice.model.*;
import com.careeroffice.service.AuthService;
import com.careeroffice.service.KeywordService;
import com.careeroffice.service.UserService;
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
import java.util.StringJoiner;

@WebServlet({"/AdminStudentsServlet", "/adminstudents"})
public class AdminStudentsServlet extends HttpServlet {
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
        UserService userService = new UserService();
        KeywordCvService keywordCvService = new KeywordCvService();
        KeywordService keywordService = new KeywordService();
        CvService cvService = new CvService();
        KeywordClassifiedPivotService keywordClassifiedPivotService = new KeywordClassifiedPivotService();

        if (!authService.isLoggedIn()) {
            response.sendRedirect("login");
            return;
        }

        if (!authService.hasRole("admin")) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        String cl_id = UrlUtil.getParameterOrDefault(request, "cl_Id", "None");
        List<User> students = userService.findStudents();
        List<String> studentKeywords = new ArrayList<>();

        if (!(cl_id.equals("None"))) {
            List<User> deleteUsers = new ArrayList<>();
            List<Keyword> classifiedKeywords = keywordClassifiedPivotService.findByClassified(Integer.parseInt(cl_id));
            for (User student : students
            ) {
                    Cv cv = cvService.findOne(student.getUsername());
                    List<Keyword> keywordCv = keywordCvService.findByCv(cv.getId());
                    boolean found = false;
                    for (Keyword clKeyword : classifiedKeywords
                    ) {
                        for (Keyword keyword : keywordCv
                        ) {
                            if (clKeyword.getSlug().equals(keyword.getSlug())) {
                                found = true;
                                break;
                            }
                        }
                    }

                    if (!found) {
                        deleteUsers.add(student);
                    }

            }
            if (deleteUsers.size() != 0){
                for (User student:deleteUsers
                     ) {
                    students.remove(student);
                }
            }


        }

        for (User student:students
             ) {
            List<String> keywords = new ArrayList<>();
            StringJoiner joiner = new StringJoiner(",");
            try {
                Cv cv = cvService.findOne(student.getUsername());
                List<Keyword> keywordCv = keywordCvService.findByCv(cv.getId());
                for (Keyword keyword:keywordCv
                     ) {
                    joiner.add(keyword.getTitle());
                }
                studentKeywords.add(joiner.toString());
            }
            catch (NullPointerException e){
                studentKeywords.add("None");
            }
        }

        request.setAttribute("users",students);
        request.setAttribute("studentKeyword",studentKeywords);


        request.getRequestDispatcher("WEB-INF/views/admin/students.jsp").forward(request, response);

    }

    /**
     * Handles all POST requests.
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request,response);
    }
}
