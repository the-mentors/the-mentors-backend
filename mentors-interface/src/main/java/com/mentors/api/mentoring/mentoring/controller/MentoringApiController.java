package com.mentors.api.mentoring.mentoring.controller;

import com.mentors.api.mentoring.mentoring.dto.AddMentoringRequest;
import com.mentors.api.mentoring.mentoring.usecase.AddMentoringUsecase;
import com.mentors.global.auth.dto.UserInfo;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/mentoring")
public class MentoringApiController {

    private final AddMentoringUsecase addMentoringUsecase;

    @PostMapping
    public ResponseEntity<Void> addMentoring(@AuthenticationPrincipal final UserInfo userInfo,
                                             @RequestBody final AddMentoringRequest request) {
        Long id = addMentoringUsecase.execute(userInfo.userId(), request);
        return ResponseEntity.created(URI.create("/api/v1/mentoring/" + id)).build();
    }
}
