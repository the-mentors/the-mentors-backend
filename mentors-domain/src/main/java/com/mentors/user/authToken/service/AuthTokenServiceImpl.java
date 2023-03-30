package com.mentors.user.authToken.service;

import com.mentors.user.authToken.AuthTokenEntity;
import com.mentors.user.authToken.AuthTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthTokenServiceImpl implements AuthTokenService {

    private final AuthTokenRepository authTokenRepository;

    @Override
    public String getAuthToken(Long key) {
        return authTokenRepository.findByKeys(key)
                .map(AuthTokenEntity::getRefreshToken)
                .orElseThrow(RuntimeException::new);
    }

    @Override
    public boolean existAuthToken(Long key) {
        return authTokenRepository.existsById(key);
    }

    @Override
    public String saveAuthToken(Long key, String refreshToken) {
        ifexistAuthTokenDelete(key);
        AuthTokenEntity authTokenEntity = createAuthTokenEntity(key, refreshToken);
        return authTokenRepository.save(authTokenEntity).getRefreshToken();
    }

    private static AuthTokenEntity createAuthTokenEntity(Long key, String refreshToken) {
        return AuthTokenEntity.builder()
                .keys(key)
                .refreshToken(refreshToken)
                .build();
    }

    private void ifexistAuthTokenDelete(Long key) {
        if(existAuthToken(key)){
            deleteByKey(key);
        }
    }

    @Override
    public void deleteByKey(Long key) {
        authTokenRepository.deleteByKeys(key);
    }
}
