package com.mentors.api.user.controller;

import com.mentors.api.user.dto.UserSignUpRequest;
import com.mentors.support.RestApiTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.mentors.api.user.common.userCommon.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpStatus.CREATED;

class UserApiControllerTest extends RestApiTest {

    @DisplayName("유저 회원가입")
    @Test
    void givenSignUpUserRequest_whenSignUpUser_thenReturnOk() {
        //given
        String url = String.format("http://localhost:%d/api/v1/users/signup", this.port);
        UserSignUpRequest userSignUpRequest = new UserSignUpRequest(email, password, username, nickname, profileUrl);

        // when
        var responseEntity = this.rest.postForEntity(url, userSignUpRequest, Object.class);

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(CREATED);
    }
}