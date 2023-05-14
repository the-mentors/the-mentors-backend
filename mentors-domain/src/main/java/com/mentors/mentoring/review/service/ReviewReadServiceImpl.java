package com.mentors.mentoring.review.service;

import com.mentors.mentoring.review.ReviewEntity;
import com.mentors.mentoring.review.ReviewMentoringEntity;
import com.mentors.mentoring.review.ReviewMentoringRepository;
import com.mentors.mentoring.review.ReviewRepository;
import com.mentors.mentoring.review.dto.ReviewResponse;
import com.mentors.mentoring.review.dto.ReviewResponses;
import com.mentors.mentoring.review.dto.ReviewStatisticResponse;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
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
    public ReviewResponses findAllByMentoringId(final Long requesterId,
                                                final Long mentoringId,
                                                final Pageable pageable) {
        Slice<ReviewResponse> response = reviewRepository.findAllByMentoringIdOrderByIdDesc(mentoringId, pageable)
                .map(review -> ReviewResponse.toDto(review, review.isOwner(requesterId)));
        return new ReviewResponses(response.getContent(), response.hasNext());
    }

    @Override
    public ReviewStatisticResponse findStatisticByMentoringId(Long mentoringId) {
        return reviewMentoringRepository.findByMentoringId(mentoringId)
                .map(ReviewStatisticResponse::toDto)
                .orElseThrow(IllegalArgumentException::new);
    }
}
