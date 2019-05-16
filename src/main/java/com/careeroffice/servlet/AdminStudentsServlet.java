package com.careeroffice.servlet;

import com.careeroffice.model.*;
import com.careeroffice.model.decorator.UserKeywordDecorator;
import com.careeroffice.service.*;
import com.careeroffice.service.factory.ServiceEnum;
import com.careeroffice.service.factory.ServiceFactory;
import com.careeroffice.service.pivot.KeywordClassifiedPivotService;
import com.careeroffice.service.pivot.KeywordCvPivotService;
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
import java.util.stream.Collectors;

@WebServlet({"/AdminStudentsServlet", "/adminstudents"})
public class AdminStudentsServlet extends HttpServlet {
    /**
     * Java related serial version UID.
     */
    private static final long serialVersionUID = 1L;


    private static final UserService userService = (UserService) ServiceFactory.getService(ServiceEnum.UserService);
    private static final UserCompanyService userCompanyService = (UserCompanyService) ServiceFactory.getService(ServiceEnum.UserCompanyService);
    private static final CvService cvService = new CvService();
    private static final KeywordCvPivotService keywordCvPivotService = (KeywordCvPivotService) ServiceFactory.getService(ServiceEnum.KeywordCvPivotService);
    private static final UserDepartmentService userDepartmentService = (UserDepartmentService) ServiceFactory.getService(ServiceEnum.UserDepartmentService);
    /**
     * Handles all GET requests.
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        AuthService authService = new AuthService(request.getSession());
        KeywordService keywordService = new KeywordService();
        CompanyService companyService = new CompanyService();
        DepartmentService departmentService = new DepartmentService();


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

        String action = UrlUtil.getParameterOrDefault(request, "action", "index");
        String name = UrlUtil.getParameterOrDefault(request, "name", "None");

        switch (action) {
            case "delete": {
                User student = userService.findOne(name);
                if (student.getUserCompany() != null) {
                    userCompanyService.delete(student.getUsername());
                }

                if (student.getUserDepartment() != null) {
                    userDepartmentService.delete(student.getUsername());
                }
                keywordCvPivotService.deleteByCvId(cvService.findOne(name).getId());
                cvService.delete(name);
                userService.delete(name);

                response.sendRedirect("adminstudents");
                break;
            }
            case "show": {
                User student = userService.findOne(name);
                request.setAttribute("student", student);
                request.setAttribute("keywords", keywordCvPivotService.findByCvId(cvService.findOne(name).getId()));
                request.getRequestDispatcher("WEB-INF/views/admin/show_student.jsp").forward(request, response);
                break;
            }
            case "edit": {
                User student = userService.findOne(name);
                request.setAttribute("student", student);
                request.setAttribute("departments", departmentService.findAll());
                List<Keyword> keywords = keywordService.findAll();
                Map<Integer, Keyword> selectedKeywords = keywordCvPivotService.findByCvId(cvService.findOne(student.getUsername()).getId()).stream()
                        .collect(Collectors.toMap(x -> x.getId(), x -> x));
                request.setAttribute("selectedKeywords", selectedKeywords);
                request.setAttribute("allKeywords", keywords);
                request.getRequestDispatcher("WEB-INF/views/admin/edit_student.jsp").forward(request, response);
                break;
            }
            default: {

                for (User student : students) {
                    int cvId = cvService.findOne(student.getUsername()).getId();

                    UserKeywordDecorator userKeywordDecorator = new UserKeywordDecorator(student);
                    userKeywordDecorator.setKeywords(keywordCvPivotService.findByCvId(cvId));
                    users.add(userKeywordDecorator);
                }

                request.setAttribute("users", users);

                request.getRequestDispatcher("WEB-INF/views/admin/students.jsp").forward(request, response);
            }
        }
    }

    /**
     * Handles all POST requests.
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        String action = UrlUtil.getParameterOrDefault(request, "action", "index");
        String name = UrlUtil.getParameterOrDefault(request, "name", "None");

        switch (action) {

            case "update": {
                User student = userService.findOne(name);

                String firstName = request.getParameter("first_name");
                String lastName = request.getParameter("last_name");
                String phoneNumber = request.getParameter("phone_number");
                String departmentId = request.getParameter("department");

                student.setName(firstName);
                student.setSurname(lastName);
                student.setPhoneNumber(phoneNumber);
                userService.update(student);

                if (departmentId != null && !departmentId.equals("nothing")) {
                    if (student.getUserDepartment() == null) {
                        userDepartmentService.save(new UserDepartment(student.getUsername(), departmentId));
                    } else {
                        student.getUserDepartment().setDepartmentId(departmentId);
                        userDepartmentService.update(student.getUserDepartment());
                    }

                } else {
                    userDepartmentService.delete(student.getUsername());
                }

                keywordCvPivotService.deleteByCvId( cvService.findOne(name).getId() );

                String[] keywordArray = request.getParameterValues("keywordIds");
                for ( String x: keywordArray) {
                    keywordCvPivotService.save(new KeywordCvPivot(Integer.valueOf(x), cvService.findOne(name).getId()));
                }

                response.sendRedirect("adminstudents?name="+ student.getUsername()+"&action=Show" );
            }
        }
    }
}
