package com.mentors.user.mapper;

import com.mentors.user.UserEntity;
import com.mentors.user.domain.User;

public class UserDomainMapper {

    public static UserEntity toEntity(User user){
        return UserEntity.builder()
                .email(user.email())
                .password(user.password())
                .username(user.username())
                .nickname(user.nickname())
                .profileUrl(user.profileUrl())
                .build();
    }
}
