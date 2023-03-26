package com.mentors.authToken.mapper;

import com.mentors.authToken.AuthTokenEntity;
import com.mentors.authToken.domain.AuthToken;

public class AuthTokenDomainMapper {
    public static AuthToken toDomain(AuthTokenEntity ae) {
        return new AuthToken(ae.getMemberId(), ae.getRefreshToken(), ae.getCreatedAt(), ae.getUpdatedAt());
    }

    public static AuthTokenEntity toEntity(AuthToken ad) {
        return AuthTokenEntity.of(ad.memberId(), ad.refreshToken());
    }
}
