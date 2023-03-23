package com.mentors.user.domain;

public record User(
                   String email,
                   String password,
                   String userName,
                   String nickName,
                   String profileUrl) {
    public static User of(
                          final String email,
                          final String password,
                          final String userName,
                          final String nickName,
                          final String profileUrl) {
        return new User(email, password, userName, nickName, profileUrl);
    }

}

