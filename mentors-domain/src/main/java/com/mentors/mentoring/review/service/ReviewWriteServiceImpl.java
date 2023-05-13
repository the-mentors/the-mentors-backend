package com.mentors.mentoring.review.service;

import com.mentors.mentoring.mentoring.MentoringEntity;
import com.mentors.mentoring.review.ReviewContent;
import com.mentors.mentoring.review.ReviewEntity;
import com.mentors.mentoring.review.ReviewMentoringEntity;
import com.mentors.mentoring.review.ReviewMentoringRepository;
import com.mentors.mentoring.review.ReviewRepository;
import com.mentors.mypage.MyPageRepository;
import com.mentors.user.user.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ReviewWriteServiceImpl implements ReviewWriteService {
    private final ReviewMentoringRepository reviewMentoringRepository;
    private final ReviewRepository reviewRepository;
    private final MyPageRepository myPageRepository;

    @Override
    public void save(final Long mentoringId) {
        reviewMentoringRepository.save(new ReviewMentoringEntity(mentoringId));
    }

    @Override
    public void addReview(final UserEntity reviewer,
                          final MentoringEntity mentoring,
                          final ReviewContent reviewContent) {
        validateStudent(reviewer.getId(), mentoring.getId());
        findReviewMentoringById(mentoring.getId()).rate(reviewContent.getRating());
        reviewRepository.save(ReviewEntity.of(reviewer, mentoring.getId(), reviewContent));
    }

    private ReviewMentoringEntity findReviewMentoringById(final Long mentoringId) {
        return reviewMentoringRepository.findByMentoringId(mentoringId)
                .orElseThrow(() -> new IllegalArgumentException());
    }

    private void validateStudent(final Long reviewerId, final Long mentoringId) {
        if (!myPageRepository.existsByMentoringIdAndMenteeId(reviewerId, mentoringId)) {
            throw new IllegalArgumentException();
        }
    }

}
