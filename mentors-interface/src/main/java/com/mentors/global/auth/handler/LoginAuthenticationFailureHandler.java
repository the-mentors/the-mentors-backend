package com.mentors.global.auth.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import java.io.IOException;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequiredArgsConstructor
public class LoginAuthenticationFailureHandler implements AuthenticationFailureHandler {

    private final ObjectMapper objectMapper;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
        setAuthenticationFailureHeader(response);

        String errorMessage = "Invalid Username or Password";
        if (exception instanceof BadCredentialsException) {
            errorMessage = exception.getMessage();
        }

        objectMapper.writeValue(response.getWriter(), ResponseEntity.badRequest().body(errorMessage));
    }

    private void setAuthenticationFailureHeader(final HttpServletResponse response) {
        response.setCharacterEncoding(UTF_8.name());
        response.setStatus(BAD_REQUEST.value());
        response.setContentType(APPLICATION_JSON_VALUE);
    }
}
