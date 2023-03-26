package com.mentors.global.jwt;

public interface TokenProvider {

    String createAccessToken(String payload);

    String createRefreshToken(String payload);

    String getPayload(final String token);

    boolean validateToken(String token);
}
