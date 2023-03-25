package com.mentors.api.user.usecase;

import com.mentors.api.user.dto.UserSignUpRequest;
import com.mentors.support.UsecaseTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static com.mentors.api.user.common.userCommon.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class SignUpUserUsecaseTest extends UsecaseTest {

    @Mock
    SignUpUserUsecase signUpUserUsecase;

    @DisplayName("유저 회원가입")
    @Test
    void givenSignUpUserRequest_whenSignUpUser_thenReturnOk() {
        //given
        UserSignUpRequest userSignUpRequest = new UserSignUpRequest(email, password, username, nickname, profileUrl);
        //when & then
        assertDoesNotThrow(() -> signUpUserUsecase.execute(userSignUpRequest));
    }
}