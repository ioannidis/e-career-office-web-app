package com.careeroffice.servlet;

import com.careeroffice.service.AuthService;
import com.careeroffice.service.ClassifiedService;
import com.careeroffice.service.factory.ServiceEnum;
import com.careeroffice.service.factory.ServiceFactory;
import com.careeroffice.util.UrlUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet({"/ExternalClassifiedsServlet", "/externalclassifieds"})
public class ExternalClassifiedsServlet extends HttpServlet {
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
        ClassifiedService classifiedService = (ClassifiedService) ServiceFactory.getService( ServiceEnum.ClassifiedService );

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
            case "create": {
                break;
            }
            case "show": {
                break;
            }
            case "edit": {
                break;
            }
            case "delete": {
                break;
            }
            default: {
                System.out.println( classifiedService.findAllByCompany( "ibm" ) );
                break;
            }
        }

        //request.getRequestDispatcher("WEB-INF/views/external/index.jsp").forward(request, response);
    }

    /**
     * Handles all POST requests.
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        AuthService authService = new AuthService(request.getSession());

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
            case "save": {
                break;
            }
            case "update": {
                break;
            }
            default: {
                doGet( request, response );
                break;
            }
        }

    }
}
