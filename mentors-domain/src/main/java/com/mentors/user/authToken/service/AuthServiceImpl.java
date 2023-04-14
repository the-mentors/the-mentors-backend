package com.mentors.user.authToken.service;

import com.mentors.user.authToken.AuthEntity;
import com.mentors.user.authToken.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthRepository authRepository;

    @Override
    public String getAuthToken(final Long key) {
        return authRepository.findByKeys(key)
                .map(AuthEntity::getRefreshToken)
                .orElseThrow(RuntimeException::new);
    }

    @Override
    public boolean existAuthToken(final Long key) {
        return authRepository.existsByKeys(key);
    }

    @Override
    public String saveAuthToken(final Long key,final String refreshToken) {
        var authEntity = createAuthTokenEntity(key, refreshToken);
        return authRepository.save(authEntity).getRefreshToken();
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
            authRepository.deleteByKeys(key);
        }
    }
}
