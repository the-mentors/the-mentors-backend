package com.mentors.api.mypage.controller;

import com.mentors.global.auth.dto.UserInfo;
import com.mentors.mypage.domain.MyPage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
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
public class AiApiController {

    @GetMapping("/mentoring/{id}/recommend")
    public ResponseEntity<Slice<MyPage>> findMentoring(@AuthenticationPrincipal final UserInfo userInfo,
                                                       @PathVariable Long id) {
        RestTemplate restTemplate = new RestTemplate();
        Long userId = userInfo.userId();
        String apiUrl = "http://localhost:5000/filter?user_id=" +userId; // 접속할 URL
        ResponseEntity<String> response = restTemplate.getForEntity(apiUrl, String.class);
        if (response.getStatusCode().is2xxSuccessful()) {
            String json = response.getBody();
            System.out.println(json);
        } else {

        };
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
