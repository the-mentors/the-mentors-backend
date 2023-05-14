package com.mentors.mypage.service;

import com.mentors.mentoring.mentoring.MentoringEntity;
import com.mentors.mypage.MyPageEntity;
import com.mentors.mypage.MyPageRepository;
import com.mentors.mypage.domain.MyPage;
import com.mentors.mypage.mapper.MyPageDomainMapper;
import com.mentors.user.user.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MyPageWriteServiceImpl implements MyPageWriteService {

    private final MyPageRepository myPageRepository;

    @Override
    public void save(MentoringEntity mentoring, UserEntity mentor, UserEntity mentee) {
        myPageRepository.save(MyPageEntity.of(mentoring,mentor,mentee));
    }
}
