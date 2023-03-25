package com.mentors;

import com.mentors.user.domain.User;

public enum UserFixture {

    USER1("user1@email.com","password","사용자1","사용자닉네임1","www.user1.com");
    private final String email;
    private final String password;
    private final String username;
    private final String nickname;
    private final String profileUrl;
    UserFixture(String email, String password, String username, String nickname, String profileUrl) {
        this.email = email;
        this.password = password;
        this.username = username;
        this.nickname = nickname;
        this.profileUrl = profileUrl;
    }

    public static User toDomain(){
        return new User(null, USER1.email, USER1.password, USER1.username, USER1.nickname, USER1.profileUrl, null,null);
    }

}
