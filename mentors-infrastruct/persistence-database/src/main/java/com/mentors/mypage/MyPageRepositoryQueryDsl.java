package com.mentors.mypage;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface MyPageRepositoryQueryDsl {
    Slice<MyPageEntity> findAllByUserId(Long userId, Pageable pageable);

    Slice<MyPageEntity> findAllByMentorId(Long mentorId, Pageable pageable);
}
