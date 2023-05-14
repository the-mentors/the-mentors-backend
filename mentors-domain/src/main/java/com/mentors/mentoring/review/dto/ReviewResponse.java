package com.mentors.mentoring.review.dto;

import com.mentors.mentoring.review.ReviewEntity;
import com.mentors.user.user.dto.UserResponse;
import java.time.LocalDateTime;

public record ReviewResponse(Long id,
                             UserResponse reviewer,
                             int rating,
                             String content,
                             boolean isOwner,
                             LocalDateTime createdAt,
                             LocalDateTime updatedAt) {

    public static ReviewResponse toDto(ReviewEntity review, boolean isOwner) {
        return new ReviewResponse(
                review.getId(),
                UserResponse.toDto(review.getReviewer()),
                review.getRatingValue(),
                review.getContent(),
                isOwner,
                review.getCreatedAt(),
                review.getUpdatedAt()
        );
    }
}
