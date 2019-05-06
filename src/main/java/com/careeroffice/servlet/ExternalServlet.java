package com.careeroffice.servlet;

import com.careeroffice.model.User;
import com.careeroffice.model.UserCompany;
import com.careeroffice.service.AuthService;
import com.careeroffice.service.CompanyService;
import com.careeroffice.service.UserCompanyService;
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

@WebServlet({"/ExternalServlet", "/external"})
public class ExternalServlet extends HttpServlet {
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

        UserService userService = (UserService) ServiceFactory.getService(ServiceEnum.UserService);
        CompanyService companyService = (CompanyService) ServiceFactory.getService(ServiceEnum.CompanyService);
        UserCompanyService userCompanyService = (UserCompanyService) ServiceFactory.getService(ServiceEnum.UserCompanyService);

        if (!authService.isLoggedIn()) {
            response.sendRedirect("login");
            return;
        }

        if (!authService.hasRole("external")) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        String action = UrlUtil.getParameterOrDefault(request, "action", "index");

        switch (action) {
            case "edit": {
                User user = userService.findOne( authService.getUser().getUsername() );

                request.setAttribute("userCompany", user.getUserCompany());
                request.setAttribute("companies", companyService.findAll());

                request.getRequestDispatcher("WEB-INF/views/external/edit.jsp").forward(request, response);
                break;
            }
            default: {
                request.getRequestDispatcher("WEB-INF/views/external/index.jsp").forward(request, response);
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
        UserCompanyService userCompanyService = (UserCompanyService) ServiceFactory.getService(ServiceEnum.UserCompanyService);

        String action = UrlUtil.getParameterOrDefault(request, "action", "index");

        if (action.equals("update")) {
            User user = authService.getUser();

            user.setName( UrlUtil.getParameterOrDefault(request, "firstname", user.getName()) );
            user.setSurname( UrlUtil.getParameterOrDefault(request, "lastname", user.getSurname()) );
            user.setEmail( UrlUtil.getParameterOrDefault(request, "email", user.getEmail()) );
            user.setPhoneNumber( UrlUtil.getParameterOrDefault(request, "phone", user.getPhoneNumber()) );

            UserCompany userCompany = userCompanyService.findOne(user.getUsername());
            userCompany.setCompanyId( UrlUtil.getParameterOrDefault(request, "companyId", userCompany.getCompanyId()) );

            userService.update(user);
            userCompanyService.update(userCompany);

            response.sendRedirect("external");
        } else {
            doGet(request, response);
        }
    }
}
