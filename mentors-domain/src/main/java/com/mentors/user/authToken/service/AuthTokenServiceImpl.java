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
    public String getAuthToken(Long userId) {
        return authTokenRepository.findById(userId).stream()
                .map(AuthTokenEntity::getRefreshToken)
                .findFirst().get();
    }

    @Override
    public boolean existAuthToken(Long userId) {
        return authTokenRepository.existsById(userId);
    }

    @Override
    public String saveAuthToken(Long userId, String refreshToken) {
        AuthTokenEntity authTokenEntity = AuthTokenEntity.builder()
                .userId(userId)
                .refreshToken(refreshToken)
                .build();
        return authTokenRepository.save(authTokenEntity).getRefreshToken();
    }
}
