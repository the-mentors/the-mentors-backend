package com.mentors.mypage.mapper;

import com.mentors.mypage.MyPageEntity;
import com.mentors.mypage.domain.MyPage;
import com.mentors.user.dto.UserResponse;

public class MyPageDomainMapper {

    public static MyPage toDomain(MyPageEntity me){
        return MyPage.of(me.getId(),
                UserResponse.toDto(me.getMentor()),
                UserResponse.toDto(me.getMentee()),
                me.getMentoring(),
                me.getCreatedAt(),
                me.getUpdatedAt());
    }

}
