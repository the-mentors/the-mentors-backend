package com.mentors.user.user.mapper;

import com.mentors.user.Role;
import com.mentors.user.user.UserEntity;
import com.mentors.user.user.domain.User;
import com.mentors.user.user.dto.UserEditRequest;
import com.mentors.user.user.dto.UserSignUpRequest;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UserDomainMapper {


    public static User toDomain(final UserSignUpRequest request) {
        return new User(null,
                request.email(),
                request.password(),
                request.username(),
                request.nickname(),
                request.profileUrl(),
                null,
                null,
                null);
    }

    public static User toDomain(final UserEditRequest request) {
        return new User(null,
                null,
                null,
                request.username(),
                request.nickname(),
                request.profileUrl(),
                null,
                null,
                null);
    }

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
                .id(user.id())
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
