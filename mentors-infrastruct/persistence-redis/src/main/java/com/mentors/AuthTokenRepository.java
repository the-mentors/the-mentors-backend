package com.mentors;

import com.mentors.token.RefreshToken;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AuthTokenRepository {

    private final RedisTemplate<String, RefreshToken> redisTemplate;


    public String setValues(Long key, RefreshToken refreshToken) {
        ValueOperations<String, RefreshToken> values = redisTemplate.opsForValue();
        values.set(getKey(key), refreshToken);

        return refreshToken.refreshToken();
    }

    public Optional<RefreshToken> getValues(Long key) {
        ValueOperations<String, RefreshToken> values = redisTemplate.opsForValue();
        RefreshToken refreshToken = values.get(getKey(key));
        return Optional.ofNullable(refreshToken);
    }

    public void deleteValues(Long key) {
        redisTemplate.delete(getKey(key));
    }

    private String getKey(Long key){
        return String.format("TOKEN:%d",key);
    }
}
