package com.mentors.user.user.dto;

import com.mentors.user.user.UserEntity;
import java.time.LocalDateTime;

public record UserResponse(Long id,
                           String email,
                           String username,
                           String nickname,
                           String profileUrl,
                           LocalDateTime createdAt) {


    public static UserResponse toDto(final UserEntity user) {
        return new UserResponse(
                user.getId(),
                user.getEmail(),
                user.getUsername(),
                user.getNickname(),
                user.getEmail(),
                user.getCreatedAt()
        );
    }
}
