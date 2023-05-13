package com.mentors.mentoring.review.service;

import com.mentors.mentoring.review.ReviewMentoringEntity;
import com.mentors.mentoring.review.ReviewMentoringRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ReviewWriteServiceImpl implements ReviewWriteService {
    private final ReviewMentoringRepository mentoringRepository;

    @Override
    public void save(final Long mentoringId) {
        mentoringRepository.save(new ReviewMentoringEntity(mentoringId));
    }
}
