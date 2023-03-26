package com.mentors.global.jwt;


import com.mentors.global.jwt.dto.AuthTokenInterface;
import com.mentors.user.authToken.service.AuthTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthTokenCreator implements TokenCreator{

    private final TokenProvider tokenProvider;
    private final AuthTokenService authTokenService;

    @Override
    public AuthTokenInterface createAuthToken(Long userId) {
        String accessToken = tokenProvider.createAccessToken(String.valueOf(userId));
        String refreshToken = createRefreshToken(userId);
        return new AuthTokenInterface(accessToken, refreshToken);
    }

    private String createRefreshToken(final Long userId) {
        if (authTokenService.existAuthToken(userId)) {
            return authTokenService.getAuthToken(userId);
        }
        String refreshToken = tokenProvider.createRefreshToken(String.valueOf(userId));
        return authTokenService.saveAuthToken(userId,refreshToken);
    }

    @Override
    public AuthTokenInterface renewAuthToken(String refreshToken) {
        tokenProvider.validateToken(refreshToken);
        Long userId = Long.valueOf(tokenProvider.getPayload(refreshToken));

        String accessTokenForRenew = tokenProvider.createAccessToken(String.valueOf(userId));
        String refreshTokenForRenew = authTokenService.getAuthToken(userId);

        AuthTokenInterface renewalAuthToken = new AuthTokenInterface(accessTokenForRenew, refreshTokenForRenew);
        return renewalAuthToken;
    }

    @Override
    public Long extractPayload(String accessToken) {
        tokenProvider.validateToken(accessToken);
        return Long.valueOf(tokenProvider.getPayload(accessToken));
    }
}
