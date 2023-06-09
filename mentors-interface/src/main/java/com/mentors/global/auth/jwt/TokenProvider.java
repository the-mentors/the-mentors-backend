package com.mentors.global.auth.jwt;

import com.mentors.user.authToken.domain.AuthToken;

import java.util.List;

public interface TokenProvider {
    String getPayload(final String token);

    boolean isTokenExpired(final String token);

    AuthToken createAuthToken(final String payload, final List<String> roles);
    AuthToken renewAuthToken(final String accessToken);
}
