package com.mentors.api.user.mapper;

import com.mentors.api.user.dto.UserSignUpRequest;
import com.mentors.user.domain.User;

public class UserApiMapper {

    public static User toUser(UserSignUpRequest usr, String password) {
        return new User(usr.email(),password, usr.userName(), usr.nickName(), usr.profileUrl());
    }
}
