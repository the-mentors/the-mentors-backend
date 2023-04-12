package com.mentors.api.user.controller;

import static com.mentors.UserFixture.toDomain;
import static com.mentors.support.fixture.UserFixture.*;
import static com.mentors.support.fixture.UserFixture.회원가입_요청정보;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpMethod.*;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.mentors.api.user.dto.UserSignUpRequest;
import com.mentors.support.RestApiTest;
import com.mentors.user.authToken.domain.AuthToken;
import com.mentors.user.user.service.UserWriteService;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

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


    @DisplayName("[API] 유저 정보 수정")
    @Test
    void givenUserIdAndUserEditRequest_whenUpdating_thenReturnIsOk() throws JsonProcessingException {
        //given
        initializeSaveUser();
        injectAccessToken();

        var url = String.format("http://localhost:%d/api/v1/users", this.port);
        var request = new HttpEntity<>(회원정보수정_요청정보(), headers);

        //when
        var responseEntity = this.rest.exchange(url, PUT, request, Object.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(OK);

    }
}