package com.mentors.global.auth.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mentors.global.auth.dto.LoginUser;
import com.mentors.global.auth.jwt.JwtTokenProvider;
import com.mentors.user.authToken.domain.AuthToken;
import com.mentors.user.authToken.service.AuthService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequiredArgsConstructor
public class LoginAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final ObjectMapper objectMapper;
    private final JwtTokenProvider jwtCreator;
    private final AuthService authService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        setAuthenticationSuccessHeader(response);

        final LoginUser user = (LoginUser) authentication.getPrincipal();
        final AuthToken authToken = jwtCreator.createAuthToken(String.valueOf(user.id()), user.role());
        authService.ifExistAuthTokenDelete(user.id());
        authService.saveAuthToken(user.id(), authToken.refreshToken());
        objectMapper.writeValue(response.getWriter(), ResponseEntity.ok(authToken));
    }



    private void setAuthenticationSuccessHeader(final HttpServletResponse response) {
        response.setCharacterEncoding(UTF_8.name());
        response.setStatus(OK.value());
        response.setContentType(APPLICATION_JSON_VALUE);
    }
}
