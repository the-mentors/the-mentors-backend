package com.mentors.api.user.auth;

import com.mentors.api.user.dto.UserSignInRequest;
import com.mentors.support.RestApiTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;

import static com.mentors.support.fixture.UserFixture.로그인_요청정보;
import static com.mentors.support.fixture.UserFixture.잘못된로그인_요청정보;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.OK;

class LoginTest extends RestApiTest {
    @DisplayName("[API] 유저 로그인")
    @Test
    void givenSignInUserRequest_whenSignInUser_thenReturnOk() {
        //given
        initializeSaveUser();
        String url = String.format("http://localhost:%d/api/v1/users/signin", this.port);
        HttpEntity<UserSignInRequest> request = new HttpEntity<>(로그인_요청정보(), headers);

        // when
        var responseEntity = this.rest.postForEntity(url, request, Object.class);

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(OK);
    }

    @DisplayName("[API] 유저 로그인 아이디가 존재하지 않는 경우 예외를 발생한다.")
    @Test
    void givenWrongSignInUserRequest_whenSignInUser_thenReturnThrows() {
        //given
        initializeSaveUser();
        String url = String.format("http://localhost:%d/api/v1/users/signin", this.port);

        HttpEntity<UserSignInRequest> request = new HttpEntity<>(잘못된로그인_요청정보(), headers);

        // when
        var responseEntity = this.rest.postForEntity(url, request, Object.class);

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(BAD_REQUEST);
    }
}