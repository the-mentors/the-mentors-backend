package com.mentors.mentoring.usecase;

import com.mentors.mentoring.mentoring.MentoringRepository;
import com.mentors.mentoring.review.ReviewMentoringEntity;
import com.mentors.mentoring.review.ReviewMentoringRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScheduleUpdateMentoringRatingUseCase {
    private final MentoringRepository mentoringRepository;
    private final ReviewMentoringRepository reviewMentoringRepository;

    @Scheduled(cron = "0 0 0 * * *")
    public void run() {
        List<ReviewMentoringEntity> reviews = reviewMentoringRepository.findAll();
        reviews.forEach(review -> {
            mentoringRepository.updateRating(review.getMentoringId(), review.getRatingAverage());
        });
    }
}
