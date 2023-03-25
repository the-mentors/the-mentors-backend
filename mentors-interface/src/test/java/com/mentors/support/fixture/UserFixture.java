package com.mentors.support.fixture;

import com.mentors.api.user.dto.UserSignUpRequest;

public enum UserFixture {
    BASIC_USER(1L, "user1@email.com", "password", "encodePassword", "username", "nickname", "www.url.com"),
    ;


    private final Long id;
    private final String email;
    private final String password;
    private final String encodePassword;
    private final String username;
    private final String nickname;
    private final String profileUrl;

    UserFixture(final Long id,
                final String email,
                final String password,
                final String encodePassword,
                final String username,
                final String nickname,
                final String profileUrl) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.encodePassword = encodePassword;
        this.username = username;
        this.nickname = nickname;
        this.profileUrl = profileUrl;
    }

    public static UserSignUpRequest 회원가입_요청정보() {
        return new UserSignUpRequest(BASIC_USER.email,
                BASIC_USER.password,
                BASIC_USER.username,
                BASIC_USER.nickname,
                BASIC_USER.profileUrl);
    }
}
