package com.mentors.api.user.usecase;

import com.mentors.api.user.dto.UserSignInRequest;
import com.mentors.global.jwt.TokenCreator;
import com.mentors.global.jwt.dto.AuthTokenInterface;
import com.mentors.user.service.UserReadService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.mentors.api.user.mapper.UserApiMapper.toDomain;


@Slf4j
@Service
@RequiredArgsConstructor
public class SignInUserUsecase {

    private final PasswordEncoder passwordEncoder;
    private final TokenCreator tokenCreator;
    private final UserReadService userReadService;

    public AuthTokenInterface execute(UserSignInRequest userSignInRequest) {
        Long userId = userReadService.signIn(toDomain(userSignInRequest), passwordEncoder.encode(userSignInRequest.password()));
        return tokenCreator.createAuthToken(userId);
    }

}