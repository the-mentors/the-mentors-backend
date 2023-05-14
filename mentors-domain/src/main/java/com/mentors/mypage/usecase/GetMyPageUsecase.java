package com.mentors.mypage.usecase;

import com.mentors.mypage.domain.MyPage;
import com.mentors.mypage.service.MyPageReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class GetMyPageUsecase {
    private final MyPageReadService myPageReadService;

    public Slice<MyPage> execute(Long userId, Pageable pageable) {
        return myPageReadService.getMyPage(userId, pageable);
    }
}
