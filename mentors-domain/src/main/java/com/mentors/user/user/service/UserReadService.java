package com.mentors.user.user.service;

import com.mentors.user.user.domain.User;

public interface UserReadService {


    User findUserById(Long userId);
}
