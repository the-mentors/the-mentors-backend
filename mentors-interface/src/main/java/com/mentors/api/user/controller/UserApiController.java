package com.mentors.api.user.controller;

import com.mentors.global.auth.dto.UserInfo;
import com.mentors.user.user.dto.UserEditRequest;
import com.mentors.user.user.dto.UserSignUpRequest;
import com.mentors.user.user.usecase.EditUserUsecase;
import com.mentors.user.user.usecase.SignUpUserUsecase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserApiController {
    private final SignUpUserUsecase signUpUserUsecase;
    private final EditUserUsecase editUserUsecase;


    @PostMapping("/signup")
    public ResponseEntity<Void> signUpUser(
            @RequestBody @Valid UserSignUpRequest userSignUpRequest
    ) {
        signUpUserUsecase.execute(userSignUpRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Void> updateUser(
            @AuthenticationPrincipal final UserInfo userInfo,
            @RequestBody @Valid final UserEditRequest request
    ){
        editUserUsecase.execute(userInfo.userId(), request);
        return ResponseEntity.ok().build();
    }

}
