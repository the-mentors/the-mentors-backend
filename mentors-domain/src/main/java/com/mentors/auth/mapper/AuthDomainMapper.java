package com.mentors.auth.mapper;

import com.mentors.auth.AuthEntity;
import com.mentors.auth.domain.Auth;

public class AuthDomainMapper {
    public static Auth toDomain(AuthEntity ae) {
        return new Auth(ae.getMemberId(), ae.getRefreshToken(), ae.getCreatedAt(), ae.getUpdatedAt());
    }

    public static AuthEntity toEntity(Auth ad) {
        return AuthEntity.of(ad.memberId(), ad.refreshToken());
    }
}
