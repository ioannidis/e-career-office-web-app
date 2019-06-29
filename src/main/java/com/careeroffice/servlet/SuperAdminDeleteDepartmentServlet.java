package com.careeroffice.servlet;

import com.careeroffice.model.Department;
import com.careeroffice.service.AuthService;
import com.careeroffice.service.DepartmentService;
import com.careeroffice.service.UserDepartmentService;
import com.careeroffice.service.factory.ServiceEnum;
import com.careeroffice.service.factory.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet({"/delete_department"})
public class SuperAdminDeleteDepartmentServlet extends HttpServlet {

    private DepartmentService departmentService = (DepartmentService) ServiceFactory.getService(ServiceEnum.DepartmentService);
    private static final UserDepartmentService userDepartmentService = (UserDepartmentService) ServiceFactory.getService(ServiceEnum.UserDepartmentService);

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        AuthService authService = new AuthService(request.getSession());
        if (!authService.isLoggedIn()) {
            response.sendRedirect("login");
            return;
        }

        if (!authService.hasRole("super_admin")) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        String id = request.getParameter("id");
        Department department = departmentService.findOne(id);

        request.setAttribute("department", department);

        request.getRequestDispatcher("WEB-INF/views/super_admin/delete_department.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String id = request.getParameter("id");
        Department department = departmentService.findOne(id);

        userDepartmentService.findAll().forEach(userDepartment -> {
            if (userDepartment.getDepartmentId().equals(department.getId())) {
                userDepartmentService.delete(userDepartment.getUsername());
            }
        });

        departmentService.delete(department.getId());

        response.sendRedirect("manage_departments");
    }
}
