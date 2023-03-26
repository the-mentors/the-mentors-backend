package com.mentors.user.user.domain;

import java.time.LocalDateTime;

public record User(
        Long id,
        String email,
        String password,
        String username,
        String nickname,
        String profileUrl,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}

