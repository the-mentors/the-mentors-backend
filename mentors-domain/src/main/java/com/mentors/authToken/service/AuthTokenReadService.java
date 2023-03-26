package com.mentors.authToken.service;


import com.mentors.authToken.domain.AuthToken;

public interface AuthTokenReadService {
    AuthToken getAuthToken(Long memberId);

    boolean existAuthToken(Long memberId);
}
