package com.mentors.user.user.usecase;

import com.mentors.user.user.dto.UserSignUpRequest;
import com.mentors.user.user.mapper.UserDomainMapper;
import com.mentors.user.user.service.UserWriteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SignUpUserUsecase {
    private final UserWriteService userWriteService;
    public void execute(final UserSignUpRequest userSignUpRequest) {
        final var user = UserDomainMapper.toDomain(userSignUpRequest);
        userWriteService.signUp(user);
    }
}
