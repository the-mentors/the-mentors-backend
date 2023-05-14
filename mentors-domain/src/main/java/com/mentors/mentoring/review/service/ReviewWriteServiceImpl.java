package com.mentors.mentoring.review.service;

import com.mentors.mentoring.mentoring.MentoringEntity;
import com.mentors.mentoring.review.ReviewContent;
import com.mentors.mentoring.review.ReviewEntity;
import com.mentors.mentoring.review.ReviewMentoringEntity;
import com.mentors.mentoring.review.ReviewMentoringRepository;
import com.mentors.mentoring.review.ReviewRepository;
import com.mentors.mypage.MyPageRepository;
import com.mentors.user.user.UserEntity;
import com.sun.jdi.request.DuplicateRequestException;
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
        validateIfNotAttender(reviewer.getId(), mentoring.getId());
        validateDuplicateWrite(reviewer, mentoring.getId());
        findReviewMentoringById(mentoring.getId()).rate(reviewContent.getRating());
        reviewRepository.save(ReviewEntity.of(reviewer, mentoring.getId(), reviewContent));
    }

    private void validateIfNotAttender(final Long reviewerId, final Long mentoringId) {
        if (!myPageRepository.existsByMentoringIdAndMenteeId(reviewerId, mentoringId)) {
            throw new IllegalArgumentException();
        }
    }

    private void validateDuplicateWrite(final UserEntity reviewer, final Long mentoringId) {
        if (reviewRepository.existsByReviewerAndMentoringId(reviewer, mentoringId)) {
            throw new DuplicateRequestException();
        }
    }

    @Override
    public void deleteById(final Long reviewId, final Long mentoringId, final Long reviewerId) {
        final ReviewEntity findReview = findReviewById(reviewId);
        validateOwner(findReview, reviewerId);

        findReviewMentoringById(mentoringId).delete(findReview.getRating());
        reviewRepository.delete(findReview);
    }

    public void validateOwner(final ReviewEntity review, final Long reviewerId) {
        if (!review.isOwner(reviewerId)) {
            throw new IllegalArgumentException();
        }
    }

    private ReviewMentoringEntity findReviewMentoringById(final Long mentoringId) {
        return reviewMentoringRepository.findByMentoringId(mentoringId)
                .orElseThrow(() -> new IllegalArgumentException());
    }

    private ReviewEntity findReviewById(final Long reviewId) {
        return reviewRepository.findById(reviewId)
                .orElseThrow(() -> new IllegalArgumentException());
    }
}
