package com.mentors.api.user.mapper;

import com.mentors.api.user.dto.UserEditRequest;
import com.mentors.api.user.dto.UserSignUpRequest;
import com.mentors.user.user.domain.User;

public class UserApiMapper {

    public static User toDomain(UserSignUpRequest request) {
        return new User(null,
                request.email(),
                request.password(),
                request.username(),
                request.nickname(),
                request.profileUrl(),
                null,
                null,
                null);
    }

    public static User toDomain(final UserEditRequest request) {
        return new User(null,
                null,
                null,
                request.username(),
                request.nickname(),
                request.profileUrl(),
                null,
                null,
                null);
    }
}
