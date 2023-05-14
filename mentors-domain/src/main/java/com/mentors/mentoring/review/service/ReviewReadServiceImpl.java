package com.mentors.mentoring.review.service;

import com.mentors.mentoring.review.ReviewMentoringRepository;
import com.mentors.mentoring.review.ReviewRepository;
import com.mentors.mentoring.review.dto.ReviewResponse;
import com.mentors.mentoring.review.dto.ReviewStatisticResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReviewReadServiceImpl implements ReviewReadService {

    private final ReviewRepository reviewRepository;
    private final ReviewMentoringRepository reviewMentoringRepository;

    @Override
    public Slice<ReviewResponse> findAllByMentoringId(Long requesterId, Long mentoringId) {
        return null;
    }

    @Override
    public ReviewStatisticResponse findStatisticByMentoringId(Long mentoringId) {
        return null;
    }
}
