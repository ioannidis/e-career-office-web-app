package com.careeroffice.servlet;

import com.careeroffice.model.Category;
import com.careeroffice.model.Classified;
import com.careeroffice.model.Keyword;
import com.careeroffice.model.decorator.ClassifiedKeywordDecorator;
import com.careeroffice.model.decorator.UserKeywordDecorator;
import com.careeroffice.service.*;
import com.careeroffice.service.factory.ServiceEnum;
import com.careeroffice.service.factory.ServiceFactory;
import com.careeroffice.service.pivot.KeywordClassifiedPivotService;
import com.careeroffice.service.pivot.KeywordCvPivotService;
import com.careeroffice.util.UrlUtil;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;


@WebServlet({"/AdminContactServlet", "/admincontact"})
public class AdminContactServlet extends HttpServlet {

    /**
     * Java related serial version UID.
     */
    private static final long serialVersionUID = 1L;

    private static final UserService userService = (UserService) ServiceFactory.getService(ServiceEnum.UserService);
    private static final ClassifiedService classifiedService = (ClassifiedService) ServiceFactory.getService( ServiceEnum.ClassifiedService );
    private static final CategoryService categoryService = (CategoryService) ServiceFactory.getService( ServiceEnum.CategoryService );
    /**
     * Handles all GET requests.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        AuthService authService = new AuthService(request.getSession());

        if (!authService.isLoggedIn()) {
            response.sendRedirect("login");
            return;
        }

        if (!authService.hasRole("admin")) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        String result = UrlUtil.getParameterOrDefault(request, "result", "None");
        String id = UrlUtil.getParameterOrDefault(request, "id", "None");

        KeywordService keywordService = new KeywordService();
        KeywordClassifiedPivotService keywordClassifiedPivotService = new KeywordClassifiedPivotService();
        KeywordCvPivotService keywordCvPivotService = new KeywordCvPivotService();
        CvService cvService = new CvService();

        List<Classified> classifieds = classifiedService.findAll();
        List<ClassifiedKeywordDecorator> classifiedList = new ArrayList<>();
        UserKeywordDecorator userKeywordDecorator = new UserKeywordDecorator(userService.findOne(id));
        userKeywordDecorator.setKeywords(keywordCvPivotService.findByCvId(cvService.findOne(id).getId()));

        for (Classified classified : classifieds
        ) {
            List<Keyword> keywordList = keywordClassifiedPivotService.findByClassified(classified.getId());
            ClassifiedKeywordDecorator classifiedKeywordDecorator = new ClassifiedKeywordDecorator(classified);
            classifiedKeywordDecorator.setKeywords(keywordList);

            // TODO : FILTER THE AVAILABLE TO SELECT CLASSIFIEDS BASED ON THE USER SKILL ??
            //if (userKeywordDecorator.getKeywords().containsAll(classifiedKeywordDecorator.getKeywords())){
                classifiedList.add(classifiedKeywordDecorator);
           // }

        }

        request.setAttribute( "classifieds", classifiedList );
        request.setAttribute("result",result);
        request.setAttribute("id",id);
        request.getRequestDispatcher("WEB-INF/views/admin/contact.jsp").forward(request, response);
    }

    /**
     * Handles all POST requests.
     */
    @Override
    protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        AuthService authService = new AuthService(request.getSession());

        if (!authService.isLoggedIn()) {
            response.sendRedirect("login");
            return;
        }

        if (!authService.hasRole("admin")) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }
        String id = UrlUtil.getParameterOrDefault(request, "id", "None");
        String adminMessage = UrlUtil.getParameterOrDefault(request, "message", "None");

        String result;

        // Recipient's email ID needs to be mentioned.
        String to = userService.findOne(id).getEmail();

        // Sender's email ID needs to be mentioned
        String from = authService.getUser().getEmail();

        // Assuming you are sending email from localhost
        String host = "localhost";

        // Get system properties object
        Properties properties = System.getProperties();

        // Setup mail server
        properties.put("mail.smtp.host", host);

        // Get the default Session object.
        Session mailSession = Session.getDefaultInstance(properties);

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(mailSession);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(to));
            // Set Subject: header field
            message.setSubject("Job Assignment");

            // Now set the actual message
            message.setText(adminMessage);

            // Send message
            Transport.send(message);
            result = "Sent message to student successfully....";
            System.out.println(result);
        } catch (MessagingException mex) {
            mex.printStackTrace();
            result = "Error: unable to send message....";
            System.out.println(result);
        }



      //  request.setAttribute("result",result);
        response.sendRedirect("WEB-INF/views/admin/index.jsp");
    }
}



