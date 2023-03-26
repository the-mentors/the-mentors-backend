package com.mentors.global.jwt.mapper;

import com.mentors.authToken.domain.AuthToken;

public class AuthInterfaceMapper {

    public static AuthToken toDomain(Long userId, String refreshToken) {
        return new AuthToken(userId, refreshToken,null,null);
    }

}
