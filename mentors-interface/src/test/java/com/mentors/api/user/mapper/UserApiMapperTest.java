package com.mentors.api.user.mapper;


import static com.mentors.support.fixture.UserFixture.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import com.mentors.api.user.dto.UserEditRequest;
import com.mentors.support.BasicClassTest;
import com.mentors.user.user.domain.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UserApiMapperTest extends BasicClassTest {

    @DisplayName("유저 회원가입 Dto를 도메인으로 변환")
    @Test
    void givenSignUpRequest_whenTransformingDomain_thenReturnDomainUser() {
        //given & when
        User user = UserApiMapper.toDomain(회원가입_요청정보());
        //then
        assertInstanceOf(User.class, user);
    }


    @DisplayName("[Mapper] 유저 정보 수정 DTO를 도메인으로 변환")
    @Test
    void givenUserEditRequest_TransformingDomain_thenReturnDomainUser() {
        //given & when
        var request = 회원정보수정_요청정보();
        var user = UserApiMapper.toDomain(request);

        //then
        assertAll(() -> {
            assertInstanceOf(UserEditRequest.class, request);
            assertInstanceOf(User.class, user);

            assertThat(user.username()).isEqualTo(request.username());
            assertThat(user.nickname()).isEqualTo(request.nickname());
            assertThat(user.profileUrl()).isEqualTo(request.profileUrl());
        });

    }
}