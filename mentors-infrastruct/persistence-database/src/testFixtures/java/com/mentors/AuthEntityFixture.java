package com.mentors;

import com.mentors.user.authToken.AuthEntity;

public enum AuthEntityFixture {

    BASIC_AUTHTOKEN(1L, 1L, "aaaaaaaaaaaa.bbbbbbbbbbbbb.cccccccccccccc");

    private final Long id;
    private final Long keys;
    private final String refreshToken;

    AuthEntityFixture(final Long id,
                           final Long keys,
                           final String refreshToken) {
        this.id = id;
        this.keys = keys;
        this.refreshToken = refreshToken;
    }

    public static AuthEntity 기본토큰생성() {
        return new AuthEntity(BASIC_AUTHTOKEN.keys, BASIC_AUTHTOKEN.refreshToken);
    }
}
