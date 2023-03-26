package com.mentors.api.user.usecase;

import static com.mentors.support.fixture.UserFixture.회원가입_요청정보;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import com.mentors.support.UsecaseTest;
import com.mentors.user.user.service.UserWriteService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.crypto.password.PasswordEncoder;

class SignUpUserUsecaseTest extends UsecaseTest {

    @InjectMocks
    private SignUpUserUsecase signUpUserUsecase;

    @Mock
    private UserWriteService userWriteService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @DisplayName("유저 회원가입")
    @Test
    void givenSignUpUserRequest_whenSignUpUser_thenReturnOk() {
        //given
        var request = 회원가입_요청정보();
        given(userWriteService.signUp(any())).willReturn(1L);

        //when & then
        assertDoesNotThrow(() -> signUpUserUsecase.execute(request));
    }
}