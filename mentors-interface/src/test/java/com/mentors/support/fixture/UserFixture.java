package com.mentors.support.fixture;

import com.mentors.api.user.dto.UserEditRequest;
import com.mentors.api.user.dto.UserSignInRequest;
import com.mentors.api.user.dto.UserSignUpRequest;

public enum UserFixture {
    BASIC_USER(1L, "user1@email.com", "password", "encodePassword", "username", "nickname", "www.url.com"),
    UPDATE_USER(1L, "update@email.com", "password", "encodePassword", "updateUsername", "updateNickname", "www.update.com"),
    WRONG_USER(1L, "wrong@email.com", "wrongpassword", "encodePassword", "wrongUsername", "wrongNickname", "www.update.com"),
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
    public static UserSignInRequest 로그인_요청정보(){
        return new UserSignInRequest(BASIC_USER.email,BASIC_USER.password);
    }

    public static UserSignInRequest 잘못된로그인_요청정보(){
        return new UserSignInRequest(BASIC_USER.email, WRONG_USER.password);
    }

    public static UserEditRequest 회원정보수정_요청정보() {
        return new UserEditRequest(UPDATE_USER.username, UPDATE_USER.nickname, UPDATE_USER.profileUrl);
    }

    public static String 기본유저_인코딩_패스워드(){
        return BASIC_USER.encodePassword;
    }
}
