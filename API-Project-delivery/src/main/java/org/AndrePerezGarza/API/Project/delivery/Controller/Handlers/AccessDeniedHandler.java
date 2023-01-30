package org.AndrePerezGarza.API.Project.delivery.Controller.Handlers;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;

import java.io.IOException;

public class AccessDeniedHandler implements org.springframework.security.web.access.AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        writeCustomResponse(response);
    }



    private void writeCustomResponse(HttpServletResponse response) {
        if (!response.isCommitted()) {
            try {
                response.setStatus(HttpStatus.FORBIDDEN.value());
                response.getWriter().write("{ \"error\": \"User is not authorized to perform this http Method.\"}");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
