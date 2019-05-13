package com.careeroffice.servlet;

import com.careeroffice.model.Department;
import com.careeroffice.service.DepartmentService;
import com.careeroffice.service.factory.ServiceEnum;
import com.careeroffice.service.factory.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet({"/edit_department"})
public class SuperAdminEditDepartmentServlet extends HttpServlet {

    private DepartmentService departmentService = (DepartmentService) ServiceFactory.getService(ServiceEnum.DepartmentService);

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String id = request.getParameter("id");
        Department department = departmentService.findOne(id);

        request.setAttribute("department", department);
        request.getRequestDispatcher("WEB-INF/views/super_admin/edit_department.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String id = request.getParameter("id");
        Department department = departmentService.findOne(id);

        String title = request.getParameter("title");

        department.setTitle(title);

        departmentService.update(department);

        response.sendRedirect("view_department?id=" + department.getId());
    }
}
