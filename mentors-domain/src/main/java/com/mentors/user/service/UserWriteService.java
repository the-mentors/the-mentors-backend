package com.mentors.user.service;

import com.mentors.user.domain.User;

public interface UserWriteService {
    Long signUp(User user);

    void updateUser(final Long userId, final User user);
}
