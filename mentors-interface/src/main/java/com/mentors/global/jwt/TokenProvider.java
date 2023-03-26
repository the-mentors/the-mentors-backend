package com.mentors.global.jwt;

public interface TokenProvider {

    String createAccessToken(String payload);

    String createRefreshToken(String payload);

    boolean validateToken(String token);
}
