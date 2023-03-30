package com.mentors.user.user.domain;

import com.mentors.authority.Authority;
import org.springframework.security.core.GrantedAuthority;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public record User(
        Long id,
        String email,
        String password,
        String username,
        String nickname,
        String profileUrl,
        ArrayList<Authority> role,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public static List<String> convertAuthorities(final User user){
        return user.role().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
    }
}



