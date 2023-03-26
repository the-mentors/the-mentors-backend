package com.mentors.global.jwt;


import com.mentors.authToken.service.AuthTokenReadService;
import com.mentors.authToken.service.AuthTokenWriteService;
import com.mentors.global.jwt.dto.AuthTokenInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.mentors.global.jwt.mapper.AuthInterfaceMapper.toDomain;

@Component
@RequiredArgsConstructor
public class AuthTokenCreator implements TokenCreator{

    private final TokenProvider tokenProvider;
    private final AuthTokenReadService authTokenReadService;
    private final AuthTokenWriteService authTokenWriteService;

    @Override
    public AuthTokenInterface createAuthToken(Long userId) {
        String accessToken = tokenProvider.createAccessToken(String.valueOf(userId));
        String refreshToken = createRefreshToken(userId);
        return new AuthTokenInterface(accessToken, refreshToken);
    }

    private String createRefreshToken(final Long userId) {
        if (authTokenReadService.existAuthToken(userId)) {
            return authTokenReadService.getAuthToken(userId).refreshToken();
        }
        String refreshToken = tokenProvider.createRefreshToken(String.valueOf(userId));
        return authTokenWriteService.saveAuthToken(toDomain(userId,refreshToken));
    }

    @Override
    public AuthTokenInterface renewAuthToken(String refreshToken) {
        tokenProvider.validateToken(refreshToken);
        Long userId = Long.valueOf(tokenProvider.getPayload(refreshToken));

        String accessTokenForRenew = tokenProvider.createAccessToken(String.valueOf(userId));
        String refreshTokenForRenew = authTokenReadService.getAuthToken(userId).refreshToken();

        AuthTokenInterface renewalAuthToken = new AuthTokenInterface(accessTokenForRenew, refreshTokenForRenew);
        renewalAuthToken.validateHasSameRefreshToken(refreshToken);
        return renewalAuthToken;
    }

    @Override
    public Long extractPayload(String accessToken) {
        tokenProvider.validateToken(accessToken);
        return Long.valueOf(tokenProvider.getPayload(accessToken));
    }
}
