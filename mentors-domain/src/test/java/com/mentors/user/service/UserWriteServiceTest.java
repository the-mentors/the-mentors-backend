package com.mentors.user.category.service;

import com.mentors.support.ServiceTest;
import com.mentors.user.domain.User;
import com.mentors.user.service.UserWriteService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.mentors.UserFixture.toDomain;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;


class UserWriteServiceTest extends ServiceTest {

    @Autowired
    private UserWriteService userWriteService;

    @DisplayName("유저 회원가입 성공")
    @Test
    void givenUserDomain_whenSignUpUser_thenReturnUserId() {
        //given
        User user = toDomain();
        // when & then
        assertDoesNotThrow(() -> userWriteService.signUp(user));
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

}