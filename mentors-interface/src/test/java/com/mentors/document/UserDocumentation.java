package com.mentors.document;

import static org.mockito.Mockito.doNothing;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.mentors.support.fixture.UserFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

public class UserDocumentation extends Documentation{

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
}
