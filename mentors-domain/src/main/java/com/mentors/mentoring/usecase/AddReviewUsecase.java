package com.mentors.mentoring.usecase;

import com.mentors.mentoring.mentoring.MentoringRepository;
import com.mentors.mentoring.review.Rating;
import com.mentors.mentoring.review.ReviewContent;
import com.mentors.mentoring.review.dto.AddReviewRequest;
import com.mentors.mentoring.review.service.ReviewWriteService;
import com.mentors.user.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AddReviewUsecase {

    private final UserRepository userRepository;
    private final MentoringRepository mentoringRepository;
    private final ReviewWriteService reviewWriteService;

    public void execute(final Long userId, final Long mentoringId, final AddReviewRequest request) {
        var findUser = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException());
        var findMentoring = mentoringRepository.findById(mentoringId).orElseThrow(() -> new IllegalArgumentException());
        var reviewContent = new ReviewContent(Rating.valueOf(request.rating()), request.content());
        reviewWriteService.addReview(findUser, findMentoring, reviewContent);
    }
}
