package com.mentors.user.service;

import com.mentors.user.domain.User;

public interface UserReadService {
    Long signIn(User user,String password);
}
