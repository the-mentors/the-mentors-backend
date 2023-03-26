package com.mentors.authToken.service;


import com.mentors.authToken.AuthTokenRepository;
import com.mentors.authToken.domain.AuthToken;
import com.mentors.authToken.mapper.AuthTokenDomainMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AuthTokenReadServiceImpl implements AuthTokenReadService {

   private final AuthTokenRepository authTokenRepository;

   @Override
   public AuthToken getAuthToken(Long userId) {
       return authTokenRepository.findById(userId).stream()
               .map(AuthTokenDomainMapper::toDomain)
               .findFirst().get();
   }

    @Override
    public boolean existAuthToken(Long userId) {
        return authTokenRepository.existsById(userId);
    }
}
