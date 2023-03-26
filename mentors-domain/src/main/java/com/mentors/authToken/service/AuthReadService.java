package com.mentors.authToken.service;


import com.mentors.authToken.domain.AuthToken;

public interface AuthReadService {
    AuthToken getAuthToken(Long memberId);

    boolean existAuthToken(Long memberId);
}
