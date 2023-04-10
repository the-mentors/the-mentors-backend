package com.mentors;

import static java.time.LocalDateTime.now;


import com.mentors.user.user.UserEntity;
import java.time.LocalDateTime;

public enum UserEntityFixture {

    BASIC_USER(1L, "user1@email.com", "password", "사용자1", "사용자닉네임1", "www.user1.com", "ROLE_USER", now(), now()),
    ;

    private final Long userId;
    private final String email;
    private final String password;
    private final String username;
    private final String nickname;
    private final String profileUrl;
    private final String role;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    UserEntityFixture(final Long userId,
                      final String email,
                      final String password,
                      final String username,
                      final String nickname,
                      final String profileUrl,
                      final String role,
                      final LocalDateTime createdAt,
                      final LocalDateTime updatedAt) {
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.username = username;
        this.nickname = nickname;
        this.profileUrl = profileUrl;
        this.role = role;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static UserEntity 기본유저_엔티티() {
        return new UserEntity(BASIC_USER.userId, BASIC_USER.email, BASIC_USER.password, BASIC_USER.username,
                BASIC_USER.nickname, BASIC_USER.profileUrl, BASIC_USER.role);
    }
}
