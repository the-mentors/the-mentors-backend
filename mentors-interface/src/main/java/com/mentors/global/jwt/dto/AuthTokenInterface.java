package com.mentors.global.jwt.dto;

public record AuthTokenInterface(
        String accessToken,
        String refreshToken) {
    public void validateHasSameRefreshToken(final String otherRefreshToken) {
        if (!refreshToken.equals(otherRefreshToken)) {
            throw new RuntimeException("회원의 리프레시 토큰이 아닙니다.");
        }
    }
}
