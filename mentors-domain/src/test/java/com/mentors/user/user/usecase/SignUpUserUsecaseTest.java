package com.mentors.user.user.usecase;

import com.mentors.support.UsecaseTest;
import com.mentors.user.user.service.UserWriteService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.crypto.password.PasswordEncoder;

import static com.mentors.UserFixture.toUserSignUpRequest;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

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
        var request = toUserSignUpRequest();
        given(userWriteService.signUp(any())).willReturn(1L);

        //when & then
        assertDoesNotThrow(() -> signUpUserUsecase.execute(request));
    }
}