package com.mentors.user.user;

import com.mentors.support.RepositoryTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.mentors.UserEntityFixture.기본유저_엔티티;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class UserRepositoryTest extends RepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @DisplayName("[Repository] 이메일로 유저 찾으면 유저를 반환한다.")
    @Test
    void giveEmail_whenFindByEmail_thenReturnUser() {
        //given
        final var savedUser = initializeSavedUser();

        //when
        final var actual = userRepository.findByEmail(savedUser.getEmail()).get();

        //then
        assertAll(() -> {
            assertThat(actual.getId()).isEqualTo(savedUser.getId());
            assertThat(actual.getEmail()).isEqualTo(savedUser.getEmail());
            assertThat(actual.getPassword()).isEqualTo(savedUser.getPassword());
            assertThat(actual.getUsername()).isEqualTo(savedUser.getUsername());
            assertThat(actual.getNickname()).isEqualTo(savedUser.getNickname());
            assertThat(actual.getProfileUrl()).isEqualTo(savedUser.getProfileUrl());
        });
    }

    @DisplayName("[Repository] 이메일의 유저가 존재하면 참을 반환한다.")
    @Test
    void giveEmail_whenExistsByEmail_thenReturnTrue() {
        //given
        final var savedUser = initializeSavedUser();

        //when
        boolean actual = userRepository.existsByEmail(savedUser.getEmail());

        //when & then
        assertThat(actual).isTrue();
    }

    @DisplayName("[Repository] 이메일의 유저가 존재하면 거짓을 반환한다.")
    @Test
    void giveUnSavedEmail_whenExistsByEmail_thenReturnFalse() {
        //given
        initializeSavedUser();
        String notExistEmail = "notExistEmail";

        //when & then
        assertThat(userRepository.existsByEmail(notExistEmail)).isFalse();
    }

}