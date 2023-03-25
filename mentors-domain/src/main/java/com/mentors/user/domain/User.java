package com.mentors.user.domain;

import java.time.LocalDateTime;

public record User(
        Long id,
        String email,
        String password,
        String userName,
        String nickName,
        String profileUrl,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}

