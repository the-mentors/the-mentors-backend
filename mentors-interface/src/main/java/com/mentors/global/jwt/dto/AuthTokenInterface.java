package com.mentors.global.jwt.dto;

public record AuthTokenInterface(
        String accessToken,
        String refreshToken) {
}
