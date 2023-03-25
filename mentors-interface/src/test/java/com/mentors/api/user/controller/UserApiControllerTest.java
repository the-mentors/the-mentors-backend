package com.mentors.api.user.controller;

import static com.mentors.support.fixture.UserFixture.회원가입_요청정보;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpStatus.CREATED;

import com.mentors.api.user.dto.UserSignUpRequest;
import com.mentors.support.RestApiTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;

class UserApiControllerTest extends RestApiTest {

    @DisplayName("유저 회원가입")
    @Test
    void givenSignUpUserRequest_whenSignUpUser_thenReturnOk() {
        //given
        String url = String.format("http://localhost:%d/api/v1/users/signup", this.port);
        HttpEntity<UserSignUpRequest> request = new HttpEntity<>(회원가입_요청정보(), headers);

        // when
        var responseEntity = this.rest.postForEntity(url, request, Object.class);

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(CREATED);
    }
}