package com.mentors.user.authToken.domain;

public record AuthToken(
        String accessToken,
        String refreshToken
) {
}
