package com.mentors.global.auth.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.mentors.user.user.domain.User;

@JsonSerialize
public record UserInfo(Long userId,
                       String email,
                       String name) {
    public static UserInfo toDto(User user){
        return new UserInfo(user.id(), user.email(), user.username());
    }
}
