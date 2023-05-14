package com.mentors.user.user.usecase;

import com.mentors.user.user.dto.UserEditRequest;
import com.mentors.user.user.mapper.UserDomainMapper;
import com.mentors.user.user.service.UserWriteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EditUserUsecase {
    private final UserWriteService userWriteService;

    public void execute(final Long userId, final UserEditRequest request) {
        log.info("Request Method : {}, User Id : {}", "UserWriteService.updateUser", userId);
        final var userDomain = UserDomainMapper.toDomain(request);
        userWriteService.updateUser(userId, userDomain);
    }
}
