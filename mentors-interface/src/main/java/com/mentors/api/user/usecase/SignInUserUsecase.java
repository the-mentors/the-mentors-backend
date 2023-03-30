package com.mentors.api.user.usecase;

import com.mentors.api.user.dto.UserSignInRequest;
import com.mentors.global.jwt.TokenCreator;
import com.mentors.user.authToken.domain.AuthToken;
import com.mentors.user.user.service.UserReadService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class SignInUserUsecase {

    private final UserReadService userReadService;

    public void execute(UserSignInRequest userSignInRequest) {
        userReadService.signIn(userSignInRequest.email(), userSignInRequest.password());

    }

}
