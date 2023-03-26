package com.mentors.authToken.service;

import com.mentors.authToken.AuthTokenRepository;
import com.mentors.authToken.domain.AuthToken;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.mentors.authToken.mapper.AuthTokenDomainMapper.toEntity;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthWriteServiceImpl implements AuthWriteService{
    private final AuthTokenRepository authTokenRepository;

    @Override
    public Long saveAuthToken(AuthToken authToken) {
        var authTokenEntity = toEntity(authToken);
        return authTokenRepository.save(authTokenEntity).getMemberId();
    }
}
