package com.mentors.mypage.domain;

import com.mentors.mentoring.mentoring.MentoringEntity;
import com.mentors.mypage.dto.MentoringMyPageResponse;
import com.mentors.user.user.dto.UserResponse;

import java.time.LocalDateTime;
import java.util.List;

public record MyPage(Long id,
                     UserResponse mentor,
                     UserResponse mentee,
                     MentoringMyPageResponse mentoring,
                     List<MentoringLinkMyPageResponse> links,
                     LocalDateTime createdAt,
                     LocalDateTime updatedAt) {

    public static MyPage of(final Long id,
                            final UserResponse mentor,
                            final UserResponse mentee,
                            final MentoringEntity mentoring,
                            final LocalDateTime createdAt,
                            final LocalDateTime updatedAt) {
        return new MyPage(id, mentor, mentee, MentoringMyPageResponse.toDto(mentoring), MentoringLinkMyPageResponse.toDto(mentoring),createdAt, updatedAt);
    }
}
