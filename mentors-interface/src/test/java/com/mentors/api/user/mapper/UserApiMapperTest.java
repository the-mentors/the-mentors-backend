package com.mentors.api.user.mapper;


import static com.mentors.support.fixture.UserFixture.*;

import com.mentors.support.BasicClassTest;
import com.mentors.user.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UserApiMapperTest extends BasicClassTest {

    @DisplayName("유저 회원가입 Dto를 도메인으로 변환")
    @Test
    void givenSignUpRequest_whenTransformingDomain_thenReturnDomainUser() {
        //given & when
        User user = UserApiMapper.toDomain(회원가입_요청정보(), 기본유저_인코딩_패스워드());
        //then
        Assertions.assertInstanceOf(User.class, user);
    }


}