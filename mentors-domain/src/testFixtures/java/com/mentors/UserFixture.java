package com.mentors;

import com.mentors.user.user.domain.User;
import com.mentors.user.user.dto.UserEditRequest;
import com.mentors.user.user.dto.UserSignUpRequest;

import java.util.List;

public enum UserFixture {

    USER1("user1@email.com","password","사용자1","사용자닉네임1","www.user1.com"),
    UPDATE_USER("update1@email.com","password","수정_사용자","수정_닉네임","www.update.com");
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
        return new User(null, USER1.email, USER1.password, USER1.username, USER1.nickname, USER1.profileUrl, null,null,null);
    }
    public static User toDomainWithRole(){
        return new User(null, USER1.email, USER1.password, USER1.username, USER1.nickname, USER1.profileUrl, List.of("ROLE_USER"),null,null);
    }

    public static User toDomainWithRoleAndUserId(){
        return new User(1L, USER1.email, USER1.password, USER1.username, USER1.nickname, USER1.profileUrl, List.of("ROLE_USER"),null,null);
    }

    public static User toUpdateUser(){
        return new User(null, UPDATE_USER.email, UPDATE_USER.password, UPDATE_USER.username, UPDATE_USER.nickname, UPDATE_USER.profileUrl, null,null,null);
    }

    public static UserSignUpRequest toUserSignUpRequest() {
        return new UserSignUpRequest(USER1.email,
                USER1.password,
                USER1.username,
                USER1.nickname,
                USER1.profileUrl);
    }

    public static UserEditRequest toUserEditRequest() {
        return new UserEditRequest(UPDATE_USER.username, UPDATE_USER.nickname, UPDATE_USER.profileUrl);
    }

}
