package com.mentors.global.auth.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mentors.global.auth.jwt.JwtTokenProvider;
import com.mentors.global.auth.utils.AuthorizationExtractor;
import com.mentors.user.authToken.service.AuthService;
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
    private final AuthService authService;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserReadService userReadService;

    @Override
    protected void doFilterInternal(final HttpServletRequest request,final HttpServletResponse response,final FilterChain filterChain)
            throws ServletException, IOException {

        try {
            final var accessToken = extractAccessTokenFromHeader(request);

            if (isTokenExpired(accessToken)) {
                final var key = jwtTokenProvider.getPayload(accessToken);
                final var refreshToken = authService.getAuthToken(Long.parseLong(key));

                if (isTokenExpired(refreshToken)) {
                    final var renewAuthToken = jwtTokenProvider.renewAuthToken(accessToken);
                    setResponseHeader(response, OK);
                    objectMapper.writeValue(response.getWriter(), renewAuthToken);
                    return;
                }
                else {
                    throw new CredentialsExpiredException("");
                }


            }

            SecurityContextHolder.getContext().setAuthentication(createAuthentication(accessToken));
        }catch (RuntimeException e){
            filterChain.doFilter(request, response);
            return;
        }
        filterChain.doFilter(request, response);
    }

    private String extractAccessTokenFromHeader(final HttpServletRequest request) {
        return AuthorizationExtractor.extract(request, BEARER);
    }

    private boolean isTokenExpired(final String token) {
        return jwtTokenProvider.isTokenExpired(token);
    }

    private static void setResponseHeader(final HttpServletResponse response,final HttpStatus status) {
        response.setCharacterEncoding(UTF_8.name());
        response.setStatus(status.value());
        response.setContentType(APPLICATION_JSON_VALUE);
    }

    private UsernamePasswordAuthenticationToken createAuthentication(final String accessToken)  {
        final var userId = (String) jwtTokenProvider.getPayload(accessToken);
        final var roles = jwtTokenProvider.getRolesFromToken(accessToken);

        final var user = userReadService.findUserById(Long.parseLong(userId));
        final var userInfo = toDto(user);
        return new UsernamePasswordAuthenticationToken(userInfo, "", roles);
    }
}
