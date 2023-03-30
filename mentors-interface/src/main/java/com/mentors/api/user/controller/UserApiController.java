package com.mentors.api.user.controller;

import com.mentors.api.user.dto.UserEditRequest;
import com.mentors.api.user.dto.UserSignInRequest;
import com.mentors.api.user.dto.UserSignUpRequest;
import com.mentors.api.user.usecase.EditUserUsecase;
import com.mentors.api.user.usecase.SignInUserUsecase;
import com.mentors.api.user.usecase.SignUpUserUsecase;
import com.mentors.global.auth.dto.UserInfo;
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
    private final SignInUserUsecase signInUserUsecase;

    @PostMapping("/signup")
    public ResponseEntity<?> signUpUser(
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
    @PostMapping("/signin")
    public ResponseEntity<Void> signUpUser(
            @RequestBody @Valid UserSignInRequest userSignInRequest
    ) {
        signInUserUsecase.execute(userSignInRequest);
        return ResponseEntity.ok().build();
    }
}
