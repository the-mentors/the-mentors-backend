package com.mentors.user.user.domain;

import java.time.LocalDateTime;
import java.util.List;

public record User(
        Long id,
        String email,
        String password,
        String username,
        String nickname,
        String profileUrl,
        List<String> role,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}



