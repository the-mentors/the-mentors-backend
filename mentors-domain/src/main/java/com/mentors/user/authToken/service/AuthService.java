package com.mentors.user.authToken.service;


public interface AuthService {
    String getAuthToken(Long key);

    boolean existAuthToken(Long key);

    String saveAuthToken(Long key,String refreshToken);
    void ifExistAuthTokenDelete(Long key);
}
