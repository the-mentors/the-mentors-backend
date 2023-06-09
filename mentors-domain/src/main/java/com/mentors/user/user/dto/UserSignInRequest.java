package com.mentors.user.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record UserSignInRequest(
        @NotNull @Email String email,
        @NotNull String password
) {
}
