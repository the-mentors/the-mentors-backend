package com.mentors.mypage.mapper;

import com.mentors.mypage.MyPageEntity;
import com.mentors.mypage.domain.MyPage;

public class MyPageDomainMapper {

    public static MyPage toDomain(MyPageEntity me){
        return MyPage.of(me.getId(),
                me.getMentor(),
                me.getMentee(),
                me.getMentoring(),
                me.getCreatedAt(),
                me.getUpdatedAt());
    }


}
