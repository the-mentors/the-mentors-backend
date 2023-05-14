package com.mentors.user.user.mapper;

import com.mentors.support.BasicClassTest;
import com.mentors.user.user.UserEntity;
import com.mentors.user.user.domain.User;
import com.mentors.user.user.dto.UserEditRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.mentors.UserFixture.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;


public class UserDomainMapperTest extends BasicClassTest {
    @DisplayName("유저 도메인을 엔티티로 변환")
    @Test
    void givenUserDomain_whenTransformingDomain_thenReturnUserEntity() {
        //given & when
        UserEntity entity = UserDomainMapper.toEntity(toDomain());

        //then
        Assertions.assertInstanceOf(UserEntity.class,entity);
    }
    @DisplayName("유저 회원가입 Dto를 도메인으로 변환")
    @Test
    void givenSignUpRequest_whenTransformingDomain_thenReturnDomainUser() {
        //given & when
        User user = UserDomainMapper.toDomain(toUserSignUpRequest());
        //then
        assertInstanceOf(User.class, user);
    }


    @DisplayName("[Mapper] 유저 정보 수정 DTO를 도메인으로 변환")
    @Test
    void givenUserEditRequest_TransformingDomain_thenReturnDomainUser() {
        //given & when
        var request = toUserEditRequest();
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
