package com.mentors.user.user.service;

import com.mentors.user.user.domain.User;

public interface UserWriteService {
    Long signUp(User user);

    void updateUser(final Long userId, final User updateUser);
}
