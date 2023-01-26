package org.AndrePerezGarza.API.Project.delivery.Controller.Configuration;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;

public class AuthenticationEntryPointHandler implements AuthenticationEntryPoint {

    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException, ServletException{
            writeCustomResponse(response);
    }

    private void writeCustomResponse(HttpServletResponse response) {
        if (!response.isCommitted()) {
            try {
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                response.getWriter().write("{ \"error\": \"User needs to authenticate in order to access.\"}");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
