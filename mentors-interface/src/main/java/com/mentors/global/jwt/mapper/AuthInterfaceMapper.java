package com.mentors.global.jwt.mapper;

import com.mentors.authToken.domain.AuthToken;

public class AuthInterfaceMapper {

    public static AuthToken toDomain(Long memberId, String refreshToken) {
        return new AuthToken(memberId, refreshToken,null,null);
    }

}
