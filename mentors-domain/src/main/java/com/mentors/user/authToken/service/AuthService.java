package com.mentors.user.authToken.service;


public interface AuthService {
    String getAuthToken(final Long key);

    boolean existAuthToken(final Long key);

    String saveAuthToken(final Long key,final String refreshToken);
    void ifExistAuthTokenDelete(final Long key);
}
