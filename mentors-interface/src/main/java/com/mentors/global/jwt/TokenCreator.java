package com.mentors.global.jwt;


import com.mentors.global.jwt.dto.AuthTokenInterface;

public interface TokenCreator {

    AuthTokenInterface createAuthToken(final Long userId);

    AuthTokenInterface renewAuthToken(final String refreshToken);

    Long extractPayload(final String accessToken);
}
