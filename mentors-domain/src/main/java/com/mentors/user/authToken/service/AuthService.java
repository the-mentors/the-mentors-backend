package com.mentors.user.authToken.service;


public interface AuthService {
    String getAuthToken(Long userId);

    boolean existAuthToken(Long userId);

    String saveAuthToken(Long userId,String refreshToken);
    void ifExistAuthTokenDelete(Long key);
}
