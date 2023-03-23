package com.mentors.user.mapper;

import com.mentors.user.UserEntity;
import com.mentors.user.domain.User;

public class UserDomainMapper {

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
