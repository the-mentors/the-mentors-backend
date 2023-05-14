package com.mentors.mentoring.review.service;

import com.mentors.mentoring.review.dto.ReviewResponses;
import com.mentors.mentoring.review.dto.ReviewStatisticResponse;
import org.springframework.data.domain.Pageable;

public interface ReviewReadService {
    ReviewResponses findAllByMentoringId(Long requesterId, Long mentoringId, Pageable pageable);
    ReviewStatisticResponse findStatisticByMentoringId(Long mentoringId);
}
