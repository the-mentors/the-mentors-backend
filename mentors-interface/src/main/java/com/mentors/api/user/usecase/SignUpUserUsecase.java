package com.mentors.api.user.usecase;

import com.mentors.api.user.dto.UserSignUpRequest;
import com.mentors.user.user.domain.User;
import com.mentors.user.user.service.UserWriteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.mentors.api.user.mapper.UserApiMapper.toDomain;

@Slf4j
@Service
@RequiredArgsConstructor
public class SignUpUserUsecase {
    private final PasswordEncoder passwordEncoder;
    private final UserWriteService userWriteService;
    public void execute(UserSignUpRequest userSignUpRequest) {
        User user = toDomain(userSignUpRequest,passwordEncoder.encode(userSignUpRequest.password()));
        userWriteService.signUp(user);
    }
}
