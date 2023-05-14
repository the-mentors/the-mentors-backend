package com.mentors.mentoring.review.usecase;

import com.mentors.mentoring.review.dto.ReviewStatisticResponse;
import com.mentors.mentoring.review.service.ReviewReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetReviewStatisticUsecase {

    private final ReviewReadService reviewReadService;

    public ReviewStatisticResponse execute(final Long mentoringId) {
        return reviewReadService.findStatisticByMentoringId(mentoringId);
    }
}
