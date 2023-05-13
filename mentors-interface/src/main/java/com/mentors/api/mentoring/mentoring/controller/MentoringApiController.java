package com.mentors.api.mentoring.mentoring.controller;

import com.mentors.common.PageResponse;
import com.mentors.global.auth.dto.UserInfo;
import com.mentors.mentoring.dto.AddMentoringRequest;
import com.mentors.mentoring.dto.MentoringListResponse;
import com.mentors.mentoring.dto.MentoringSingleResponse;
import com.mentors.mentoring.usecase.AddMentoringUsecase;
import com.mentors.mentoring.usecase.DeleteMentoringUsecase;
import com.mentors.mentoring.usecase.GetAllMentoringUsecase;
import com.mentors.mentoring.usecase.GetOneMentoringUsecase;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/mentoring")
public class MentoringApiController {

    private final AddMentoringUsecase addMentoringUsecase;
    private final DeleteMentoringUsecase deleteMentoringUsecase;
    private final GetAllMentoringUsecase getAllMentoringUsecase;
    private final GetOneMentoringUsecase getOneMentoringUsecase;

    @PostMapping
    public ResponseEntity<Void> addMentoring(@AuthenticationPrincipal final UserInfo userInfo,
                                             @RequestBody final AddMentoringRequest request) {
        Long id = addMentoringUsecase.execute(userInfo.userId(), request);
        return ResponseEntity.created(URI.create("/api/v1/mentoring/" + id)).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@AuthenticationPrincipal final UserInfo userInfo,
                                           @PathVariable final Long id) {
        deleteMentoringUsecase.execute(userInfo.userId(), id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<PageResponse<MentoringListResponse>> getAll(@AuthenticationPrincipal final UserInfo userInfo,
                                                                      @RequestParam(defaultValue = "0", required = false) int page,
                                                                      @RequestParam(defaultValue = "20", required = false) int size) {
        var response = getAllMentoringUsecase.execute(page, size);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MentoringSingleResponse> getById(@AuthenticationPrincipal final UserInfo userInfo,
                                                           @PathVariable final Long id) {
        var response = getOneMentoringUsecase.execute(id);
        return ResponseEntity.ok(response);
    }
}
