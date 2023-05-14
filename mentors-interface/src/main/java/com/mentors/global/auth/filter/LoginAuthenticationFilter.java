package com.mentors.global.auth.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mentors.user.user.dto.UserSignInRequest;
import com.mentors.global.auth.token.LoginAuthenticationToken;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Objects;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

public class LoginAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
    private final ObjectMapper objectMapper;
    private static final String loginUrl = "/api/v1/users/signin";

    public LoginAuthenticationFilter(final ObjectMapper objectMapper) {
        super(new AntPathRequestMatcher(loginUrl));
        this.objectMapper = objectMapper;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException {
        if (!isContentTypeJson(request)) {
            throw new RemoteException();
        }

        final UserSignInRequest requestDto = objectMapper.readValue(request.getReader(), UserSignInRequest.class);

        if (isNoUserInformation(requestDto)) {
            throw new RemoteException();
        }

        final LoginAuthenticationToken authenticationToken = new LoginAuthenticationToken(requestDto, requestDto.password());
        return getAuthenticationManager().authenticate(authenticationToken);
    }

    private boolean isNoUserInformation(final UserSignInRequest request) {
        return ObjectUtils.isEmpty(request.email()) || ObjectUtils.isEmpty(request.password());
    }

    private boolean isContentTypeJson(final HttpServletRequest request) {
        String header = request.getContentType();
        return Objects.nonNull(header) &&
                StringUtils.hasText(header) &&
                header.contains(APPLICATION_JSON_VALUE);
    }
}
