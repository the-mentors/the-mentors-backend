package com.mentors.mentoring.dto;

import com.mentors.mentoring.mentoring.MentoringEntity;
import com.mentors.user.dto.UserResponse;

public record MentoringListResponse(Long id,
                                    UserResponse userResponse,
                                    String title,
                                    String content,
                                    String thumbnail,
                                    Integer price) {
    public static MentoringListResponse toDto(MentoringEntity mentoring) {
        return new MentoringListResponse(
                mentoring.getId(),
                UserResponse.toDto(mentoring.getUser()),
                mentoring.getTitle(),
                mentoring.getContent(),
                mentoring.getThumbnail(),
                mentoring.getPrice()
        );
    }
}
