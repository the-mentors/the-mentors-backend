package com.mentors.mypage.domain;

import com.mentors.mentoring.mentoring.MentoringEntity;
import com.mentors.user.user.UserEntity;

import java.time.LocalDateTime;

public record MyPage(Long id,
                     UserEntity mentor,
                     UserEntity mentee,
                     MentoringEntity mentoring,
                     LocalDateTime createdAt,
                     LocalDateTime updatedAt) {

    public static MyPage of(final Long id,
                            final UserEntity mentor,
                            final UserEntity mentee,
                            final MentoringEntity mentoring,
                            final LocalDateTime createdAt,
                            final LocalDateTime updatedAt) {
        return new MyPage(id, mentor, mentee, mentoring, createdAt, updatedAt);
    }
}
