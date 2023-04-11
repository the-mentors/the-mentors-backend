package com.mentors.support;

import static com.mentors.UserFixture.toDomain;
import static com.mentors.support.fixture.UserFixture.로그인_요청정보;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mentors.lib.DatabaseCleaner;
import com.mentors.user.user.service.UserWriteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

@Profile("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class RestApiTest {

    @LocalServerPort
    protected int port;
    protected TestRestTemplate rest;

    @Autowired
    private UserWriteService userWriteService;

    @Autowired
    private DatabaseCleaner databaseCleaner;

    @Autowired
    protected ObjectMapper objectMapper;

    protected HttpHeaders headers;

    @BeforeEach
    void initialize(){
        rest = new TestRestTemplate();
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        databaseCleaner.execute();
    }


    protected void initializeSaveUser(){
        userWriteService.signUp(toDomain());
    }

    protected void injectAccessToken() throws JsonProcessingException {
        var url = String.format("http://localhost:%d/api/v1/users/signin", this.port);
        var request = new HttpEntity<>(로그인_요청정보(), headers);
        var json = this.rest.postForEntity(url, request, String.class).getBody();
        BodyAccessTokenParser bodyParser = objectMapper.readValue(json, BodyAccessTokenParser.class);
        headers.add(HttpHeaders.AUTHORIZATION, bodyParser.accessToken());
    }

    private record BodyAccessTokenParser(Body body) {
        public record Body(String accessToken, String refreshToken){ }
        public String accessToken(){
            return "Bearer " + body.accessToken;
        }
    }
}
