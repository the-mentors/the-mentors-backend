package com.mentors.mentoring.review.service;

import com.mentors.mentoring.review.dto.ReviewResponses;
import com.mentors.mentoring.review.dto.ReviewStatisticResponse;

public interface ReviewReadService {
    ReviewResponses findAllByMentoringId(Long requesterId, Long mentoringId);
    ReviewStatisticResponse findStatisticByMentoringId(Long mentoringId);
}
