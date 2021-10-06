package com.billy.testdemo.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authenticationException)
            throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        Exception exception = (Exception) request.getAttribute("exception");
        String message;

        if (exception != null) {
            if (exception.getCause() != null) {
                message = exception.getCause().toString() + " " + exception.getMessage();
            } else {
                message = exception.getMessage();
            }
            byte[] body = new ObjectMapper()
                    .writeValueAsBytes(Collections.singletonMap("error", message));
            response.getOutputStream().write(body);

        } else {

            if (authenticationException.getCause() != null) {
                message = authenticationException.getCause().toString() + " " + authenticationException.getMessage();
            } else {
                message = authenticationException.getMessage();
            }
            byte[] body = new ObjectMapper()
                    .writeValueAsBytes(Collections.singletonMap("error", message));
            response.getOutputStream().write(body);
        }
    }
}
