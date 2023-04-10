package com.mentors.user.user;

import static com.mentors.UserEntityFixture.기본유저_엔티티;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.mentors.mentoring.mentoring.Title;
import com.mentors.user.user.UserEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UserEntityTest {

    @DisplayName("[Entity] 유저 엔티티 수정")
    @Test
    void givenUpdateUserEntity_whenUpdating_thenReturnNon() {
        //given
        UserEntity updateUser = UserEntity.builder()
                .username("수정 이름")
                .nickname("수정 닉네임")
                .profileUrl("수정 URL")
                .build();

        //when
        UserEntity actualUser = 기본유저_엔티티();
        actualUser.update(updateUser);

        //then
        assertAll(() -> {
            assertThat(actualUser.getUsername()).isEqualTo(updateUser.getUsername());
            assertThat(actualUser.getNickname()).isEqualTo(updateUser.getNickname());
            assertThat(actualUser.getProfileUrl()).isEqualTo(updateUser.getProfileUrl());
        });
    }

    @DisplayName("[Entity] 유저 엔티티간 아이디로 비교시 아이디가 같을 경우 True 반환")
    @Test
    void givenMatchUserId_whenIsSame_thenReturnTrue() {
        //given
        UserEntity user = 기본유저_엔티티();
        Long matchUserId = 1L;

        //when & then
        assertTrue(user.isSameId(matchUserId));
    }

    @DisplayName("[Entity] 유저 엔티티간 아이디로 비교시 아이디가 다를 경우 False 반환")
    @Test
    void givenNonMatchUserId_whenIsSame_thenReturnFalse() {
        //given
        UserEntity user = 기본유저_엔티티();
        Long nonMatchUserId = 2L;

        //when & then
        assertFalse(user.isSameId(nonMatchUserId));
    }
}