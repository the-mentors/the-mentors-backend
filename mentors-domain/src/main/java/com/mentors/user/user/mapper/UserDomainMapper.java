package com.mentors.user.user.mapper;

import com.mentors.authority.Authority;
import com.mentors.user.Role;
import com.mentors.user.user.UserEntity;
import com.mentors.user.user.domain.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;

public class UserDomainMapper {

    public static UserEntity toEntityWithRoleUser(final User user,final String password) {
        return UserEntity.builder()
                .email(user.email())
                .password(password)
                .username(user.username())
                .nickname(user.nickname())
                .profileUrl(user.profileUrl())
                .role(Role.USER.toString())
                .build();
    }

    public static UserEntity toEntity(final User user) {
        return UserEntity.builder()
                .email(user.email())
                .password(user.password())
                .username(user.username())
                .nickname(user.nickname())
                .profileUrl(user.profileUrl())
                .build();
    }

    public static User toDomain(final UserEntity user) {
        return new User(user.getId(),
                user.getEmail(),
                user.getPassword(),
                user.getUsername(),
                user.getNickname(),
                user.getProfileUrl(),
                convertAuthoritiesToString(user.getAuthorities()),
                user.getCreatedAt(),
                user.getUpdatedAt());
    }

    public static List<String> convertAuthoritiesToString(final Collection<? extends GrantedAuthority> authorities) {
        return authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
    }
}
