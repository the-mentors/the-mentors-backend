package com.mentors.document;

import com.mentors.support.fixture.UserFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static com.mentors.document.DocumentationFixture.*;
import static com.mentors.support.fixture.UserFixture.로그인_요청정보;
import static com.mentors.support.fixture.UserFixture.회원가입_요청정보;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserDocumentation extends Documentation {

    @DisplayName("[Docs]유저 회원가입")
    @Test
    void givenSignUpUserRequest_whenSignUpUser_thenReturnIsCreated() throws Exception {
        willDoNothing().given(signUpUserUsecase).execute(회원가입_요청정보());
        // expected
        mockMvc.perform(post("/api/v1/users/signup")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(회원가입_요청정보())
                        )
                )
                .andExpect(status().isCreated())
                .andDo(document("user/post/signup",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        requestFields(
                                fieldWithPath("email").description("이메일"),
                                fieldWithPath("password").description("비밀번호"),
                                fieldWithPath("username").description("이름"),
                                fieldWithPath("nickname").description("닉네임"),
                                fieldWithPath("profileUrl").description("프로필 이미지")
                        )
                ));

    }
    @DisplayName("[Docs] 유저 정보 수정")
    @Test
    void givenUpdateUserRequest_whenUpdating_thenReturnStatusIsOk() throws Exception {
        //given
        final Long userId = 1L;
        final var request = UserFixture.회원정보수정_요청정보();
        doNothing().when(editUserUsecase).execute(userId, request);

        //document
        mockMvc.perform(put("/api/v1/users")
                        .param("id", userId.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andDo(document("user/put/update",
                                preprocessRequest(prettyPrint()),
                                preprocessResponse(prettyPrint())
                        )
                )
                .andExpect(status().isOk());
    }

    @DisplayName("[Docs] 유저 로그인")
    @Test
    void givenSignInUserRequest_whenSignIn_thenReturnAccessTokenAndRefreshToken() throws Exception {
        //given
        final String userId = "1";
        when(userContextService.loadUserByUsername(EMAIL)).thenReturn(USERCONTEST_FIXTURE);
        when(passwordEncoder.matches(any(),any())).thenReturn(true);
        when(jwtCreator.createAuthToken(userId, ROLE)).thenReturn(AUTHTOKEN_FIXTURE);
        //document
        mockMvc.perform(post("/api/v1/users/signin")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(로그인_요청정보())))
                .andDo(print())
                .andDo(document("user/post/signin",
                                preprocessRequest(prettyPrint()),
                                preprocessResponse(prettyPrint()),
                                requestFields(
                                        fieldWithPath("email").description("이메일"),
                                        fieldWithPath("password").description("비밀번호")
                                )
                        )
                )
                .andExpect(status().isOk());
    }
}
