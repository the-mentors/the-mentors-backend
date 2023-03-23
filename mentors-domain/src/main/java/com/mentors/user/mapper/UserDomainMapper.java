package com.mentors.user.mapper;

import com.mentors.user.UserEntity;
import com.mentors.user.domain.User;

public class UserDomainMapper {

    public static User toDomain(UserEntity ue){
        return User.of(
                ue.getEmail(),
                ue.getPassword(),
                ue.getUsername(),
                ue.getNickname(),
                ue.getProfileUrl());
    }

    public static UserEntity toEntity(User u){
        return UserEntity.builder()
                .email(u.email())
                .password(u.password())
                .username(u.userName())
                .nickname(u.nickName())
                .profileUrl(u.profileUrl())
                .build();
    }
}
