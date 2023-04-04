package com.mentors.global.auth.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mentors.global.auth.jwt.JwtTokenProvider;
import com.mentors.global.auth.utils.AuthorizationExtractor;
import com.mentors.user.authToken.service.AuthTokenService;
import com.mentors.user.user.service.UserReadService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static com.mentors.global.auth.dto.UserInfo.toDto;
import static com.mentors.global.auth.utils.AuthorizationType.BEARER;
import static java.nio.charset.StandardCharsets.UTF_8;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final ObjectMapper objectMapper;
    private final AuthTokenService authTokenService;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserReadService userReadService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        try {
            final String accessToken = extractAccessTokenFromHeader(request);

            if (isAccessTokenExpired(accessToken)) {
                final var key = jwtTokenProvider.getPayload(accessToken);
                final var refreshToken = authTokenService.getAuthToken(Long.parseLong(key));

                if (!jwtTokenProvider.validateToken(refreshToken)) {
                    throw new CredentialsExpiredException("");
                }

                final var renewAuthToken = jwtTokenProvider.renewAuthToken(accessToken);
                setResponseHeader(response, OK);
                objectMapper.writeValue(response.getWriter(), renewAuthToken);
                return;
            }

            SecurityContextHolder.getContext().setAuthentication(createAuthentication(accessToken));
        }catch (RuntimeException e){
            filterChain.doFilter(request, response);
            return;
        }
        filterChain.doFilter(request, response);
    }

    private String extractAccessTokenFromHeader(HttpServletRequest request) {
        return AuthorizationExtractor.extract(request, BEARER);
    }

    private boolean isAccessTokenExpired(String accessToken) {
        return !jwtTokenProvider.validateToken(accessToken);
    }

    private static void setResponseHeader(HttpServletResponse response, HttpStatus status) {
        response.setCharacterEncoding(UTF_8.name());
        response.setStatus(status.value());
        response.setContentType(APPLICATION_JSON_VALUE);
    }

    private UsernamePasswordAuthenticationToken createAuthentication(String accessToken)  {
        final var userId = (String) jwtTokenProvider.getPayload(accessToken);
        final var roles = jwtTokenProvider.getRolesFromToken(accessToken);

        final var user = userReadService.findUserById(Long.parseLong(userId));
        final var userInfo = toDto(user);
        return new UsernamePasswordAuthenticationToken(userInfo, "", roles);
    }
}
