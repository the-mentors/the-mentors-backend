package com.mentors.api.user.mapper;


import com.mentors.support.BasicClassTest;
import com.mentors.user.user.domain.User;
import com.mentors.user.user.dto.UserEditRequest;
import com.mentors.user.user.mapper.UserDomainMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.mentors.support.fixture.UserFixture.회원가입_요청정보;
import static com.mentors.support.fixture.UserFixture.회원정보수정_요청정보;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

class UserApiMapperTest extends BasicClassTest {

    @DisplayName("유저 회원가입 Dto를 도메인으로 변환")
    @Test
    void givenSignUpRequest_whenTransformingDomain_thenReturnDomainUser() {
        //given & when
        User user = UserDomainMapper.toDomain(회원가입_요청정보());
        //then
        assertInstanceOf(User.class, user);
    }


    @DisplayName("[Mapper] 유저 정보 수정 DTO를 도메인으로 변환")
    @Test
    void givenUserEditRequest_TransformingDomain_thenReturnDomainUser() {
        //given & when
        var request = 회원정보수정_요청정보();
        var user = UserDomainMapper.toDomain(request);

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