package com.mentors.user.authToken.service;

import com.mentors.AuthTokenRepository;
import com.mentors.token.RefreshToken;
import com.mentors.user.authToken.AuthEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthTokenRepository authRepository;

    @Override
    public String getAuthToken(final Long key) {
        return authRepository.getValues(key).orElseThrow().refreshToken();
    }

    @Override
    public boolean existAuthToken(final Long key) {
        return authRepository.getValues(key).isPresent();
    }

    @Override
    public String saveAuthToken(final Long key,final String refreshToken) {
        authRepository.setValues(key,new RefreshToken(refreshToken));
        return refreshToken;
    }

    private static AuthEntity createAuthTokenEntity(final Long key,final String refreshToken) {
        return AuthEntity.builder()
                .keys(key)
                .refreshToken(refreshToken)
                .build();
    }

    @Override
    public void ifExistAuthTokenDelete(final Long key) {
        if(existAuthToken(key)){
            authRepository.deleteValues(key);
        }
    }
}
