package com.mentors.global.jwt;


import com.mentors.user.authToken.domain.AuthToken;

public interface TokenCreator {

    AuthToken createAuthToken(final Long userId);

    AuthToken renewAuthToken(final String refreshToken);

    Long extractPayload(final String accessToken);
}
