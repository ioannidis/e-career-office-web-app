package com.careeroffice.servlet;

import com.careeroffice.model.Category;
import com.careeroffice.service.CategoryService;
import com.careeroffice.service.factory.ServiceEnum;
import com.careeroffice.service.factory.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet({"/edit_category"})
public class SuperAdminEditCategory extends HttpServlet {

    private static final CategoryService categoryService = (CategoryService) ServiceFactory.getService(ServiceEnum.CategoryService);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Category category = categoryService.findOne(id);

        request.setAttribute("category", category);

        request.getRequestDispatcher("WEB-INF/views/super_admin/edit_category.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Category category = categoryService.findOne(id);

        String title = request.getParameter("title");
        String slug = request.getParameter("slug");

        category.setTitle(title);
        category.setSlug(slug);

        categoryService.update(category);

        response.sendRedirect("view_category?id=" + category.getId());
    }
}