package com.example.Employee.Management.System.security;

import com.example.Employee.Management.System.service.EmailService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.Date;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private EmailService emailService;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        FilterChain chain,
                                        Authentication authentication) throws IOException, ServletException {
        AuthenticationSuccessHandler.super.onAuthenticationSuccess(request, response, chain, authentication);
    }
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        String username = authentication.getName();

        String recipientEmail = "example@mail.com";  // recipient mail or your mail

        String subject = "Login Alert Notification" ;

        String body = "Hey Buddy! User : " + username.toUpperCase() + " has successfully logged to the employee-management-system in at : " + new Date();

        try {

            emailService.sendEmail(recipientEmail, body, subject);

        }catch (Exception e) {
            e.printStackTrace();
        }
        response.setContentType("text/plain");
        response.setStatus(HttpServletResponse.SC_OK);

        //Custom String to the response

        String customMessage = "Login Successful for USER : " + username.toUpperCase() + " at " + new Date() + "\n" + "\n" + "\n"
                + "you can get the employees details by using /api/employees/get ";

        response.getWriter().write(customMessage);
        response.getWriter().flush();
    }
}
