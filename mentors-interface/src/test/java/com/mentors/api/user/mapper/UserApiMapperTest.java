package com.mentors.api.user.mapper;

import com.mentors.api.user.dto.UserSignUpRequest;
import com.mentors.support.BasicClassTest;
import com.mentors.user.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.mentors.api.user.common.userCommon.*;

class UserApiMapperTest extends BasicClassTest {

    @DisplayName("유저 회원가입 Dto를 도메인으로 변환")
    @Test
    void givenSignUpRequest_whenTransformingDomain_thenReturnDomainUser() {
        //given & when
        User user = UserApiMapper.toDomain(new UserSignUpRequest(email, password, username, nickname, profileUrl), encodePassword);
        //then
        Assertions.assertInstanceOf(User.class, user);
    }


}