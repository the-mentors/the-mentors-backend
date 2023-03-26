package com.mentors.user.authToken.service;


public interface AuthTokenService {
    String getAuthToken(Long userId);

    boolean existAuthToken(Long userId);

    String saveAuthToken(Long userId,String refreshToken);
}
