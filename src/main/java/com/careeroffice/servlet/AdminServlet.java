package com.careeroffice.servlet;

import com.careeroffice.model.User;
import com.careeroffice.model.UserCompany;
import com.careeroffice.service.AuthService;
import com.careeroffice.service.UserService;
import com.careeroffice.service.factory.ServiceEnum;
import com.careeroffice.service.factory.ServiceFactory;
import com.careeroffice.util.UrlUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet({"/AdminServlet", "/admin"})
public class AdminServlet extends HttpServlet {
    /**
     * Java related serial version UID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Handles all GET requests.
     */
    protected void doGet( HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        AuthService authService = new AuthService(request.getSession());
        UserService userService = (UserService) ServiceFactory.getService( ServiceEnum.UserService);

        if (!authService.isLoggedIn()) {
            response.sendRedirect("login");
            return;
        }

        if (!authService.hasRole("admin")) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        String action = UrlUtil.getParameterOrDefault(request, "action", "index");

        switch (action) {
            case "edit": {
                User user = userService.findOne( authService.getUser().getUsername() );

                request.setAttribute("userCompany", user.getUserCompany());

                request.getRequestDispatcher("WEB-INF/views/admin/edit_admin_details.jsp").forward(request, response);
                break;
            }
            default: {
                request.getRequestDispatcher("WEB-INF/views/admin/index.jsp").forward(request, response);
                break;
            }
        }
    }

    /**
     * Handles all POST requests.
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        AuthService authService = new AuthService(request.getSession());
        UserService userService = (UserService) ServiceFactory.getService(ServiceEnum.UserService);

        String action = UrlUtil.getParameterOrDefault(request, "action", "index");

        if (action.equals("update")) {
            User user = authService.getUser();

            user.setName( UrlUtil.getParameterOrDefault(request, "firstname", user.getName()) );
            user.setSurname( UrlUtil.getParameterOrDefault(request, "lastname", user.getSurname()) );
            user.setEmail( UrlUtil.getParameterOrDefault(request, "email", user.getEmail()) );
            user.setPhoneNumber( UrlUtil.getParameterOrDefault(request, "phone", user.getPhoneNumber()) );

            userService.update(user);

            response.sendRedirect("admin");
        } else {
            doGet(request, response);
        }

    }
}
