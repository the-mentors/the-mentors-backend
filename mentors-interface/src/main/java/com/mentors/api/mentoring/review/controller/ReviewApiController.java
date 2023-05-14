package com.mentors.api.mentoring.review.controller;

import com.mentors.global.auth.dto.UserInfo;
import com.mentors.mentoring.review.dto.AddReviewRequest;
import com.mentors.mentoring.review.dto.ReviewResponse;
import com.mentors.mentoring.review.dto.ReviewStatisticResponse;
import com.mentors.mentoring.review.usecase.AddReviewUsecase;
import com.mentors.mentoring.review.usecase.DeleteReviewUsecase;
import com.mentors.mentoring.review.usecase.GetAllReviewUsecase;
import com.mentors.mentoring.review.usecase.GetReviewStatisticUsecase;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ReviewApiController {

    private final AddReviewUsecase addReviewUsecase;
    private final DeleteReviewUsecase deleteReviewUsecase;
    private final GetReviewStatisticUsecase getReviewStatisticUsecase;
    private final GetAllReviewUsecase getAllReviewUsecase;

    @PostMapping("/mentoring/{id}/reviews")
    public ResponseEntity<Void> addReview(
            @AuthenticationPrincipal final UserInfo userInfo,
            @PathVariable final Long id,
            @RequestBody final AddReviewRequest request) {
        addReviewUsecase.execute(userInfo.userId(), id, request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/mentoring/{mentoringId}/reviews/{reviewId}")
    public ResponseEntity<Void> deleteReview(
            @AuthenticationPrincipal final UserInfo userInfo,
            @PathVariable final Long mentoringId,
            @PathVariable final Long reviewId) {
        deleteReviewUsecase.execute(reviewId, mentoringId, userInfo.userId());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/mentoring/{id}/reviews")
    public ResponseEntity<Slice<ReviewResponse>> findAllById(
            @AuthenticationPrincipal final UserInfo userInfo,
            @PathVariable Long id){
        Slice<ReviewResponse> response = getAllReviewUsecase.execute(userInfo.userId(), id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/mentoring/{id}/reviews/statistic")
    public ResponseEntity<ReviewStatisticResponse> findStatisticByMentoringId(
            @AuthenticationPrincipal final UserInfo userInfo,
            @PathVariable Long id){
        ReviewStatisticResponse response = getReviewStatisticUsecase.execute(id);
        return ResponseEntity.ok(response);
    }
}
