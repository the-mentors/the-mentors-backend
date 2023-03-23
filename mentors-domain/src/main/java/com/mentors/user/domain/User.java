package com.mentors.user.domain;

public record User(
                   String email,
                   String password,
                   String userName,
                   String nickName,
                   String profileUrl) {
}

