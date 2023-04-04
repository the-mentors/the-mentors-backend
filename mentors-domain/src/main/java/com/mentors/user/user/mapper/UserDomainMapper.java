package com.mentors.user.user.mapper;

import com.mentors.global.common.Role;
import com.mentors.user.user.UserEntity;
import com.mentors.user.user.domain.User;

import java.util.ArrayList;

public class UserDomainMapper {

    public static UserEntity toEntityWithRoleUser(User user, String password) {
        return UserEntity.builder()
                .email(user.email())
                .password(password)
                .username(user.username())
                .nickname(user.nickname())
                .profileUrl(user.profileUrl())
                .role(Role.USER)
                .build();
    }

    public static UserEntity toEntity(User user) {
        return UserEntity.builder()
                .email(user.email())
                .password(user.password())
                .username(user.username())
                .nickname(user.nickname())
                .profileUrl(user.profileUrl())
                .build();
    }

    public static User toDomain(UserEntity user) {
        ArrayList<String> arrayListRole = changeAuthoritiesToString(user.getAuthorities().toArray());
        return new User(user.getId(),
                user.getEmail(),
                user.getPassword(),
                user.getUsername(),
                user.getNickname(),
                user.getProfileUrl(),
                arrayListRole,
                user.getCreatedAt(),
                user.getUpdatedAt());
    }

    private static ArrayList<String> changeAuthoritiesToString(Object[] authorities) {
        ArrayList<String> arrayListRole =new ArrayList<>();
        for(Object role:authorities){
            arrayListRole.add(role.toString());
        }
        return arrayListRole;
    }
}
