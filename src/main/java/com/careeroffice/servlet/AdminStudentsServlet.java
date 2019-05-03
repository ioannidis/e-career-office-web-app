package com.careeroffice.servlet;

import com.careeroffice.model.Skills;
import com.careeroffice.model.User;
import com.careeroffice.service.AuthService;
import com.careeroffice.service.UserService;
import com.careeroffice.util.UrlUtil;

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
        if (!authService.isLoggedIn()) {
            response.sendRedirect("login");
            return;
        }

        if (!authService.hasRole("admin")) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        String classifiedSkills = UrlUtil.getParameterOrDefault(request, "classifiedSkills", "None");
        List<User> students = userService.findStudents();

        if (!(classifiedSkills.equals("None"))){
            List<User> deleteUsers = new ArrayList<>();
            for (User student:students
                 ) {
                String skills = student.getUserSkills().getSkills();
                if (!(skills.equals("None"))) {
                    String[] all = skills.split(",");
                    String[] classifiedAll = classifiedSkills.split(",");
                    boolean found = false;
                    for (String skill : classifiedAll
                    ) {
                        for (String studentSkill : all
                        ) {
                            if (studentSkill.equals(skill)) {
                                found = true;
                                break;
                            }

                        }
                        if (found){break;}
                    }
                    if (!found) {
                        deleteUsers.add(student);
                    }

                }
                else{
                    deleteUsers.add(student);
                }
            }

            if (deleteUsers.size()!= 0){
                for (User user:deleteUsers
                     ) {
                    students.remove(user);
                }
            }
        }



        request.setAttribute("users",students);


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
