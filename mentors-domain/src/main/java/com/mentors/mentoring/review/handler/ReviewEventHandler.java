package com.mentors.mentoring.review.handler;

import com.mentors.mentoring.review.event.ReviewSaveEvent;
import com.mentors.mentoring.review.service.ReviewWriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionalEventListener;


@Async
@Service
@RequiredArgsConstructor
public class ReviewEventHandler {

    private final ReviewWriteService reviewWriteService;

    @TransactionalEventListener
    public void save(ReviewSaveEvent reviewSaveEvent) {
        reviewWriteService.save(reviewSaveEvent.mentoringId());
    }
}
