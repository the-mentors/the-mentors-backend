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
    public String getAuthToken(Long key) {
        return authRepository.findByKeys(key)
                .map(AuthEntity::getRefreshToken)
                .orElseThrow(RuntimeException::new);
    }

    @Override
    public boolean existAuthToken(Long key) {
        return authRepository.existsByKeys(key);
    }

    @Override
    public String saveAuthToken(Long key, String refreshToken) {
        AuthEntity authEntity = createAuthTokenEntity(key, refreshToken);
        return authRepository.save(authEntity).getRefreshToken();
    }

    private static AuthEntity createAuthTokenEntity(Long key, String refreshToken) {
        return AuthEntity.builder()
                .keys(key)
                .refreshToken(refreshToken)
                .build();
    }

    @Override
    public void ifExistAuthTokenDelete(Long key) {
        if(existAuthToken(key)){
            authRepository.deleteByKeys(key);
        }
    }
}
