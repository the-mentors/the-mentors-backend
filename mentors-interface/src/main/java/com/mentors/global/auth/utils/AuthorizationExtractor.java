package com.mentors.global.auth.utils;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import java.util.Objects;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AuthorizationExtractor {

    public static String extract(final HttpServletRequest request, final AuthorizationType type) {
        final var typeToLowerCase = type.toLowerCase();
        final var typeLength = typeToLowerCase.length() + 1;
        final var header = request.getHeader(AUTHORIZATION);

        validateAuthorizationFormat(header, typeToLowerCase);
        return header.substring(typeLength).trim();
    }

    private static void validateAuthorizationFormat(final String header, final String lowerCase) {
        if (Objects.isNull(header) || !StringUtils.hasText(header) || !header.toLowerCase().startsWith(lowerCase)) {
            throw new IllegalArgumentException();
        }
    }
}
