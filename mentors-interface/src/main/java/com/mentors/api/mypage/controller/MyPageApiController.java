package com.mentors.api.mypage.controller;

import com.mentors.global.auth.dto.UserInfo;
import com.mentors.mypage.domain.MyPage;
import com.mentors.mypage.usecase.GetMyPageUsecase;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/mypage")
public class MyPageApiController {

    private final GetMyPageUsecase getMyPageUsecase;

    @GetMapping
    public ResponseEntity<Slice<MyPage>> addMentoring(@AuthenticationPrincipal final UserInfo userInfo,
                                                      Pageable pageable) {
        Slice<MyPage> execute = getMyPageUsecase.execute(userInfo.userId(), pageable);
        return ResponseEntity.ok(execute);
    }


}
