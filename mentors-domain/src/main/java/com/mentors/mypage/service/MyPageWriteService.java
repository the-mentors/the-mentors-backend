package com.mentors.mypage.service;


import com.mentors.mentoring.mentoring.MentoringEntity;
import com.mentors.mypage.MyPageEntity;
import com.mentors.mypage.domain.MyPage;
import com.mentors.user.user.UserEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface MyPageWriteService {
     void save(MentoringEntity mentoring, UserEntity mentor,UserEntity mentee);
}
