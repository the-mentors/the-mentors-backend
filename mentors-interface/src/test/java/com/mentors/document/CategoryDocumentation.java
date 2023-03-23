package com.mentors.document;

import static com.mentors.document.DocumentationFixture.CATEGORIES_FIXTURE;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CategoryDocumentation extends Documentation{

    @DisplayName("[Docs] 카테고리 전체 조회")
    @Test
    void givenNoParameters_whenSearchingCategories_thenReturnCategoryResponse() throws Exception {

        when(getAllCategoryUsecase.execute()).thenReturn(CATEGORIES_FIXTURE);

        mockMvc.perform(get("/api/v1/categories"))
                .andDo(print())
                .andDo(document("category/get/allCategories",
                                preprocessRequest(prettyPrint()),
                                preprocessResponse(prettyPrint())
                        )
                )
                .andExpect(status().isOk());
    }
}
