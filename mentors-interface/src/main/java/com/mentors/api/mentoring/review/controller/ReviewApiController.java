package com.mentors.api.mentoring.review.controller;

import com.mentors.global.auth.dto.UserInfo;
import com.mentors.mentoring.review.dto.AddReviewRequest;
import com.mentors.mentoring.usecase.AddReviewUsecase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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

    @PostMapping("/mentoring/{id}/reviews")
    public ResponseEntity<Void> addReview(
            @AuthenticationPrincipal final UserInfo userInfo,
            @PathVariable final Long id,
            @RequestBody final AddReviewRequest request) {
        addReviewUsecase.execute(userInfo.userId(), id, request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
