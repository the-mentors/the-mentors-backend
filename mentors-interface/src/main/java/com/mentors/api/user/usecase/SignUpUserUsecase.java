package com.mentors.api.user.usecase;

import com.mentors.api.user.dto.UserSignUpRequest;
import com.mentors.user.domain.User;
import com.mentors.user.servuce.UserWriteServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.mentors.api.user.mapper.UserApiMapper.toUser;

@Slf4j
@Service
@RequiredArgsConstructor
public class SignUpUserUsecase {
    private final PasswordEncoder passwordEncoder;
    private final UserWriteServiceImpl userWriteService;
    public void execute(UserSignUpRequest userSignUpRequest) {
        User user = toUser(userSignUpRequest,passwordEncoder.encode(userSignUpRequest.password()));
        userWriteService.signUp(user);
    }
}
