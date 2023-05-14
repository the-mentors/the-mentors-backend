package com.mentors.global.auth.jwt;

import com.mentors.user.authToken.domain.AuthToken;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class JwtTokenProvider implements TokenProvider {

    private final SecretKey secretKey;
    private final long accessTokenValidityInMilliseconds;
    private final long refreshTokenValidityInMilliseconds;

    public JwtTokenProvider(@Value("${jwt.token.secret}") final String secretKey,
                            @Value("${jwt.token.access.expiration}") long accessTokenValidityInMilliseconds,
                            @Value("${jwt.token.refresh.expiration}") long refreshTokenValidityInMilliseconds) {
        this.secretKey = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
        this.accessTokenValidityInMilliseconds = accessTokenValidityInMilliseconds;
        this.refreshTokenValidityInMilliseconds = refreshTokenValidityInMilliseconds;
    }

    private String createAccessToken(final String payload, final List<String> roles) {
        return createToken(payload, accessTokenValidityInMilliseconds, roles);
    }

    private String createRefreshToken(final String payload, final List<String> roles) {
        return createToken(payload, refreshTokenValidityInMilliseconds, roles);
    }

    private String createToken(final String payload,final Long validityInMilliseconds, final List<String> roles) {
        var now = new Date();
        var validity = new Date(now.getTime() + validityInMilliseconds);

        Map<String, Object> claims = new HashMap<>();
        claims.put("roles", roles);
        claims.put("payload", payload);

        return Jwts.builder()
                .setSubject(payload)
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

    @Override
    public String getPayload(final String accessToken) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(accessToken)
                .getBody().get("payload", String.class);
    }


    public Collection<GrantedAuthority> getRolesFromToken(final String accessToken) {
        return getRoleStrings(accessToken).stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    private List<String> getRoleStrings(final String accessToken) {
        return (List<String>) Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(accessToken).getBody().get("roles");
    }

    @Override
    public boolean isTokenExpired(final String token) {
        try {
            if (!StringUtils.hasText(token) ||!StringUtils.hasText(secretKey.toString()) )
                throw new IllegalArgumentException();

            return Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token).getBody().getExpiration().before(new Date());

        } catch (final JwtException | IllegalArgumentException e) {
            return false;
        }
    }


    public AuthToken createAuthToken(final String payload,final List<String> roles) {
        final var accessToken = createAccessToken(payload, roles);
        final var refreshToken = createRefreshToken(payload, roles);
        return new AuthToken(accessToken, refreshToken);
    }

    @Override
    public AuthToken renewAuthToken(final String accessToken) {
        final var payload = (String) getPayload(accessToken);
        final var roles = getRoleStrings(accessToken);

        final var accessTokenForRenew = createAccessToken(payload, roles);
        final var refreshTokenForRenew = createRefreshToken(payload, roles);

        return new AuthToken(accessTokenForRenew, refreshTokenForRenew);
    }


}
