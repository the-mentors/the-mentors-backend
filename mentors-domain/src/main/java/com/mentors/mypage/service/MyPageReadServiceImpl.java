package com.mentors.mypage.service;

import com.mentors.mypage.MyPageRepository;
import com.mentors.mypage.domain.MyPage;
import com.mentors.mypage.mapper.MyPageDomainMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MyPageReadServiceImpl implements MyPageReadService {

    private final MyPageRepository myPageRepository;

    @Override
    public Slice<MyPage> getMyPage(Long userId, Pageable pageable) {
        return myPageRepository
                .findAllByUserId(userId, pageable)
                .map(MyPageDomainMapper::toDomain);
    }

    @Override
    public Slice<MyPage> getMyPageByMentorId(Long userId, Pageable pageable) {
        return myPageRepository
                .findAllByMentorId(userId, pageable)
                .map(MyPageDomainMapper::toDomain);
    }

}
