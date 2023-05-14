package com.mentors.global.auth.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.io.IOException;

public class LoginDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(final HttpServletRequest request, final HttpServletResponse response
            , final AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access is denied");
    }
}
