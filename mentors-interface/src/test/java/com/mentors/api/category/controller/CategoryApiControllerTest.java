package com.mentors.api.category.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpStatus.OK;

import com.mentors.api.category.dto.CategoryRequest;
import com.mentors.support.RestApiTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CategoryApiControllerTest extends RestApiTest {

    @DisplayName("[RestApi] 카테고리 전체 조회")
    @Test
    void givenNoParameters_whenSearchingCategories_thenReturnCategoryResponse() {
        //given
        String url = String.format("http://localhost:%d/api/v1/categories", this.port);

        // when
        var responseEntity = this.rest.getForEntity(url, CategoryRequest.class);

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(OK);
        assertThat(responseEntity.getBody().categories().size()).isNotNull();
    }
}