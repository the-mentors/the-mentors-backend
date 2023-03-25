package com.mentors.user.service;

import static com.mentors.UserEntityFixture.*;
import static com.mentors.UserFixture.toDomain;
import static com.mentors.UserFixture.toUpdateUser;
import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import com.mentors.UserEntityFixture;
import com.mentors.support.ServiceTest;
import com.mentors.user.UserEntity;
import com.mentors.user.UserRepository;
import com.mentors.user.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


class UserWriteServiceTest extends ServiceTest {

    @Autowired
    private UserWriteService userWriteService;

    @Autowired
    private UserRepository userRepository;


    @DisplayName("유저 회원가입 성공")
    @Test
    void givenUserDomain_whenSignUpUser_thenReturnUserId() {
        //given
        User user = toDomain();

        // when & then
        assertAll(() -> {
            assertDoesNotThrow(() -> userWriteService.signUp(user));
            assertTrue(userRepository.existsByEmail(user.email()));
        });
    }
    @Test
    @DisplayName("회원 회원가입시 중복된 정보로 인한 예외발생")
    void givenUserDomain_whenSignUpUser_thenTho(){
        //given
        userWriteService.signUp(toDomain());
        //when & then
        assertThatThrownBy(() -> userWriteService.signUp(toDomain()))
                .isInstanceOf(RuntimeException.class);
    }

    @DisplayName("[Service] 유저 정보 수정 성공")
    @Test
    void givenUserIdAndUpdateUserDomain_whenUpdating_thenReturnVoid() {
        //given
        Long id = initializeUserSave();
        User updateUser = toUpdateUser();

        //when
        userWriteService.updateUser(id, updateUser);

        //then
        UserEntity actual = userRepository.findById(id).get();
        assertAll(() -> {
            assertThat(actual.getId()).isEqualTo(id);
            assertThat(actual.getUsername()).isEqualTo(updateUser.userName());
            assertThat(actual.getNickname()).isEqualTo(updateUser.nickName());
            assertThat(actual.getProfileUrl()).isEqualTo(updateUser.profileUrl());
        });
    }


    @DisplayName("[Service] 유저 정보 수정시, 존재하지 않는 유저 아이디일 경우 예외발생")
    @Test
    void givenNonexistentIdAndUpdateUserDomain_whenUpdating_thenThrowsException(){
        //given
        Long nonExistentId = 0L;
        User updateUser = toUpdateUser();

        //when & then
        assertThatThrownBy(() -> userWriteService.updateUser(nonExistentId, updateUser))
                .isInstanceOf(RuntimeException.class);
    }

    private Long initializeUserSave(){
        return userRepository.save(기본유저_엔티티()).getId();
    }
}