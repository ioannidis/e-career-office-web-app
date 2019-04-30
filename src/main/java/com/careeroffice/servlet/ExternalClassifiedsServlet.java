package com.careeroffice.servlet;

import com.careeroffice.model.Classified;
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
        Integer id = Integer.parseInt(UrlUtil.getParameterOrDefault(request, "id", ""));

        switch (action) {
            case "create": {
                request.getRequestDispatcher("WEB-INF/views/classified/create.jsp").forward(request, response);
                break;
            }
            case "show": {
                request.setAttribute( "classified", classifiedService.findOne(id) );
                request.getRequestDispatcher("WEB-INF/views/classified/show.jsp").forward(request, response);
                break;
            }
            case "edit": {
                request.setAttribute( "classified", classifiedService.findOne(id) );
                request.getRequestDispatcher("WEB-INF/views/classified/edit.jsp").forward(request, response);
                break;
            }
            case "delete": {
                classifiedService.delete(id);
                response.sendRedirect("externalclassifieds");
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
        Integer id = Integer.parseInt(UrlUtil.getParameterOrDefault(request, "id", ""));

        switch (action) {
            case "save": {
                classifiedService.save( new Classified( "New Job", "tralala", "ibm", 1 ) );
                break;
            }
            case "update": {
                Classified classified = classifiedService.findOne(id);

//                classified.setId(UrlUtil.getParameterOrDefault(request, "id", classified.getId()));
                classified.setTitle(UrlUtil.getParameterOrDefault(request, "title", classified.getTitle()));
                classified.setContent(UrlUtil.getParameterOrDefault(request, "content", classified.getContent()));
                classified.setCompanyId(UrlUtil.getParameterOrDefault(request, "companyId", classified.getCompanyId()));
                classified.setCategoryId(UrlUtil.getParameterOrDefault(request, "categoryId", classified.getCategoryId()));

                System.out.println( classified );
                classifiedService.update(classified);
                System.out.println( classified );


                response.sendRedirect("externalclassifieds");
                break;
            }
            default: {
                doGet( request, response );
                break;
            }
        }

    }
}
