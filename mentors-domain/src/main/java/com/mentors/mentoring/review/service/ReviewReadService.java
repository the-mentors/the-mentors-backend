package com.mentors.mentoring.review.service;

import com.mentors.mentoring.review.dto.ReviewResponse;
import com.mentors.mentoring.review.dto.ReviewStatisticResponse;
import org.springframework.data.domain.Slice;

public interface ReviewReadService {
    Slice<ReviewResponse> findAllByMentoringId(Long requesterId, Long mentoringId);
    ReviewStatisticResponse findStatisticByMentoringId(Long mentoringId);
}
