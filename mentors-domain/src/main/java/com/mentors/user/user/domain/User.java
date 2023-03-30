package com.mentors.user.user.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;

public record User(
        Long id,
        String email,
        String password,
        String username,
        String nickname,
        String profileUrl,
        ArrayList<String> role,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}



