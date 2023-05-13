package com.mentors.mypage.service;


import com.mentors.mypage.domain.MyPage;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface MyPageReadService {
      Slice<MyPage> getMyPage(Long userId, Pageable pageable);
}
