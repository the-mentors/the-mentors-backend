package com.mentors.api.user.mapper;

import com.mentors.api.user.dto.UserSignUpRequest;
import com.mentors.support.BasicClassTest;
import com.mentors.user.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UserApiMapperTest extends BasicClassTest {

    private final String email = "user1@email.com";
    private final String password = "password";
    private final String encodePassword = "encodePassword";
    private final String username = "username";
    private final String nickname = "nickname";
    private final String profileUrl = "www.url.com";

    @DisplayName("유저 회원가입 Dto를 도메인으로 변환")
    @Test
    void givenSignUpRequest_whenTransformingDomain_thenReturnDomainUser() {
        //given & when
        User user = UserApiMapper.toDomain(new UserSignUpRequest(email, password, username, nickname, profileUrl), encodePassword);
        //then
        Assertions.assertInstanceOf(User.class, user);
    }


}