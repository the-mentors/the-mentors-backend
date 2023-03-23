package com.mentors.api.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserSignUpRequest(
        @NotNull @Email String email,
        @NotNull String password,
        @NotNull String userName,
        @Size(min = 2, max = 14) String nickName,
        String profileUrl) {

    public static UserSignUpRequest of(final String email,
                                       final String password,
                                       final String userName,
                                       final String nickName,
                                       final String profileUrl) {
        return new UserSignUpRequest(email, password, userName, nickName, profileUrl);
    }

}
