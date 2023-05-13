package com.mentors.mentoring.review.service;

import com.mentors.mentoring.mentoring.MentoringEntity;
import com.mentors.mentoring.review.ReviewContent;
import com.mentors.user.user.UserEntity;

public interface ReviewWriteService {

    void save(final Long mentoringId);

    void addReview(final UserEntity reviewer, final MentoringEntity mentoring, final ReviewContent reviewContent);

}
