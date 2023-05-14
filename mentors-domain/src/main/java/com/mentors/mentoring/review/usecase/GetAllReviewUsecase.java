package com.mentors.mentoring.review.usecase;

import com.mentors.mentoring.review.dto.ReviewResponses;
import com.mentors.mentoring.review.service.ReviewReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetAllReviewUsecase {

    private final ReviewReadService reviewReadService;

    public ReviewResponses execute(final Long requesterId, final Long mentoringId, final Pageable pageable) {
        return reviewReadService.findAllByMentoringId(requesterId, mentoringId, pageable);
    }
}
