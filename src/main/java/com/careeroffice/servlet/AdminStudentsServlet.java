package com.careeroffice.servlet;

import com.careeroffice.model.*;
import com.careeroffice.model.decorator.UserKeywordDecorator;
import com.careeroffice.service.AuthService;
import com.careeroffice.service.KeywordService;
import com.careeroffice.service.UserService;
import com.careeroffice.service.factory.ServiceEnum;
import com.careeroffice.service.factory.ServiceFactory;
import com.careeroffice.service.pivot.KeywordClassifiedPivotService;
import com.careeroffice.service.CvService;
import com.careeroffice.service.pivot.KeywordCvPivotService;
import com.careeroffice.service.student.KeywordCvService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
        CvService cvService = new CvService();
        KeywordCvPivotService keywordCvPivotService = (KeywordCvPivotService) ServiceFactory.getService( ServiceEnum.KeywordCvPivotService );

        if (!authService.isLoggedIn()) {
            response.sendRedirect("login");
            return;
        }

        if (!authService.hasRole("admin")) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        List<User> students = userService.findStudents();

        List<UserKeywordDecorator> users = new ArrayList<>();
        for ( User student: students ) {
            int cvId = cvService.findOne( student.getUsername() ).getId();

            UserKeywordDecorator userKeywordDecorator = new UserKeywordDecorator( student );
            userKeywordDecorator.setKeywords( keywordCvPivotService.findByCvId( cvId ) );
            users.add( userKeywordDecorator );
        }

        request.setAttribute("users", users);

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
