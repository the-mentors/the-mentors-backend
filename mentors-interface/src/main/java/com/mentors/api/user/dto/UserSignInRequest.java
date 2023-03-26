package com.mentors.api.user.dto;

public record UserSignInRequest(
        String email,
        String password
) {
}
