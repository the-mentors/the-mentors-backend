package com.mentors.global.auth.jwt;

import com.mentors.user.authToken.domain.AuthToken;

import java.util.List;

public interface TokenProvider {

    String createAccessToken(final String payload, final List<String> roles);

    String createRefreshToken(final String payload, final List<String> roles);

    String getPayload(final String token);

    boolean validateToken(final String token);

    AuthToken createAuthToken(final String payload, final List<String> roles);
    AuthToken renewAuthToken(final String accessToken);
}
