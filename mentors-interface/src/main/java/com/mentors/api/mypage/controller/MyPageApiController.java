package com.mentors.api.mypage.controller;

import com.mentors.global.auth.dto.UserInfo;
import com.mentors.mypage.domain.MyPage;
import com.mentors.mypage.usecase.AddMyPageUsecase;
import com.mentors.mypage.usecase.GetMyPageUsecase;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class MyPageApiController {

    private final GetMyPageUsecase getMyPageUsecase;
    private final AddMyPageUsecase addMyPageUsecase;

    @GetMapping("/mypages")
    public ResponseEntity<Slice<MyPage>> findMentoring(@AuthenticationPrincipal final UserInfo userInfo,
                                                      Pageable pageable) {
        Slice<MyPage> execute = getMyPageUsecase.execute(userInfo.userId(), pageable);
        return ResponseEntity.ok(execute);
    }

    @GetMapping("/mypages/writer")
    public ResponseEntity<Slice<MyPage>> findMyMentoring(@AuthenticationPrincipal final UserInfo userInfo,
                                                       Pageable pageable) {
        Slice<MyPage> execute = getMyPageUsecase.myPages(userInfo.userId(), pageable);
        return ResponseEntity.ok(execute);
    }

    @PostMapping("/mentoring/{id}/subscribe")
    public ResponseEntity<Void> addMentoring(@AuthenticationPrincipal final UserInfo userInfo,
                                             @PathVariable Long id) {
        addMyPageUsecase.execute(userInfo.userId(), id);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


}
