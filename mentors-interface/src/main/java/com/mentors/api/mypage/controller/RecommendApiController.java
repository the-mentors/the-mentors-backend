package com.mentors.api.mypage.controller;

import com.mentors.global.auth.dto.UserInfo;
import com.mentors.mentoring.recommend.RecommendApiComponent;
import com.mentors.mentoring.recommend.RecommendResponse;
import com.mentors.mentoring.recommend.RecommendUsecase;
import com.mentors.mypage.domain.MyPage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class RecommendApiController {

    private final RecommendApiComponent recommendApiComponent;
    private final RecommendUsecase recommendUsecase;

    @GetMapping("/mentoring/recommend")
    public ResponseEntity<Slice<MyPage>> findMentoring(@AuthenticationPrincipal final UserInfo userInfo) {
        RecommendResponse response = recommendApiComponent.recommend(userInfo.userId());
        recommendUsecase.execute(response.recommend());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
