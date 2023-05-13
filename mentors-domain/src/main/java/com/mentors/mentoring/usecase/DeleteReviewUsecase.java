package com.mentors.mentoring.usecase;

import com.mentors.mentoring.review.service.ReviewWriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteReviewUsecase {

    private final ReviewWriteService reviewWriteService;

    public void execute(final Long reviewId, final Long mentoringId, final Long reviewerId) {
        reviewWriteService.deleteById(reviewId, mentoringId, reviewerId);
    }
}
