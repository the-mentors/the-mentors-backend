package com.mentors.global.jwt;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;

import javax.crypto.SecretKey;
import java.util.Date;

public class JwtTokenProvider implements TokenProvider {

    private final SecretKey key;
    private final long accessTokenValidityInMilliseconds;
    private final long refreshTokenValidityInMilliseconds;

    public JwtTokenProvider(@Value("${jwt.token.secret}") final SecretKey key,
                            @Value("${jwt.token.access.expiration}") long accessTokenValidityInMilliseconds,
                            @Value("${jwt.token.refresh.expiration}") long refreshTokenValidityInMilliseconds) {
        this.key = key;
        this.accessTokenValidityInMilliseconds = accessTokenValidityInMilliseconds;
        this.refreshTokenValidityInMilliseconds = refreshTokenValidityInMilliseconds;
    }


    @Override
    public String createAccessToken(String payload) {
        return createToken(payload,accessTokenValidityInMilliseconds);
    }
    @Override
    public String createRefreshToken(String payload) {
        return createToken(payload,refreshTokenValidityInMilliseconds);
    }

    private String createToken(String payload,Long validityInMilliseconds) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);

        return Jwts.builder()
                .setSubject(payload)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    @Override
    public String getPayload(final String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    @Override
    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            claims.getBody()
                    .getExpiration()
                    .before(new Date());
            return true;
        } catch (final JwtException | IllegalArgumentException e) {
            throw new RuntimeException("권한이 없습니다.");
        }
    }

}
