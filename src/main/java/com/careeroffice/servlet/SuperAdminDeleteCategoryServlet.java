package com.careeroffice.servlet;

import com.careeroffice.model.Category;
import com.careeroffice.service.CategoryService;
import com.careeroffice.service.ClassifiedService;
import com.careeroffice.service.factory.ServiceEnum;
import com.careeroffice.service.factory.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet({"/delete_category"})
public class SuperAdminDeleteCategoryServlet extends HttpServlet {

    private static final ClassifiedService classifiedService = (ClassifiedService) ServiceFactory.getService(ServiceEnum.ClassifiedService);
    private static final CategoryService categoryService = (CategoryService) ServiceFactory.getService(ServiceEnum.CategoryService);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Category category = categoryService.findOne(id);

        request.setAttribute("category", category);

        request.getRequestDispatcher("WEB-INF/views/super_admin/delete_category.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        classifiedService.findAll().forEach(classified -> {
            if (classified.getCategoryId() == id) {
                classifiedService.delete(classified.getId());
            }
        });

        categoryService.delete(id);
        response.sendRedirect("manage_categories");
    }
}