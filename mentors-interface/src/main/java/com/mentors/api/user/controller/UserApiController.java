package com.mentors.api.user.controller;

import com.mentors.api.user.dto.UserEditRequest;
import com.mentors.api.user.dto.UserSignUpRequest;
import com.mentors.api.user.usecase.EditUserUsecase;
import com.mentors.api.user.usecase.SignUpUserUsecase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserApiController {
    private final SignUpUserUsecase signUpUserUsecase;
    private final EditUserUsecase editUserUsecase;

    @PostMapping("/signup")
    public ResponseEntity<?> signUpUser(
            @RequestBody @Valid UserSignUpRequest userSignUpRequest
    ) {
        signUpUserUsecase.execute(userSignUpRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Void> updateUser(
            @RequestParam final Long id,
            @RequestBody @Valid final UserEditRequest request
    ){
        editUserUsecase.execute(id, request);
        return ResponseEntity.ok().build();
    }
}
