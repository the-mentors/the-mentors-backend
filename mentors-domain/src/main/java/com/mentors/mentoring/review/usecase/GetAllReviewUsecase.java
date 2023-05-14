package com.mentors.mentoring.review.usecase;

import com.mentors.mentoring.review.dto.ReviewResponse;
import com.mentors.mentoring.review.service.ReviewReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetAllReviewUsecase {

    private final ReviewReadService reviewReadService;

    public Slice<ReviewResponse> execute(final Long requesterId, final Long mentoringId) {
        return reviewReadService.findAllByMentoringId(requesterId, mentoringId);
    }
}
