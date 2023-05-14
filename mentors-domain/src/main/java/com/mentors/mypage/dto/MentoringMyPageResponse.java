package com.mentors.mypage.dto;

import com.mentors.mentoring.mentoring.MentoringEntity;

public record MentoringMyPageResponse(Long id,
                                      String title,
                                      String content,
                                      String thumbnail,
                                      Integer price
) {

    public static MentoringMyPageResponse toDto(final MentoringEntity mentoring) {
        return new MentoringMyPageResponse(
                mentoring.getId(),
                mentoring.getTitle(),
                mentoring.getContent(),
                mentoring.getThumbnail(),
                mentoring.getPrice()

        );
    }
}
