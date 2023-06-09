package com.mentors.user.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserSignUpRequest(
        @NotNull @Email String email,
        @NotNull String password,
        @NotNull String username,
        @Size(min = 2, max = 14) String nickname,
        String profileUrl) {

}
