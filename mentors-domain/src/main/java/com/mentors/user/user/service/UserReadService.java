package com.mentors.user.user.service;

import com.mentors.user.user.domain.User;

public interface UserReadService {
    Long signIn(String userEmail,String password);

    User findUserById(Long userId);
}
