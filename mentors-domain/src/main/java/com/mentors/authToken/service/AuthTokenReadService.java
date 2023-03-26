package com.mentors.authToken.service;


import com.mentors.authToken.domain.AuthToken;

public interface AuthTokenReadService {
    AuthToken getAuthToken(Long userId);

    boolean existAuthToken(Long userId);
}
