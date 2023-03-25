package com.mentors.api.category.mapper;

import static com.mentors.CategoryFixture.toCategories;

import com.mentors.api.category.dto.CategoryRequest;
import com.mentors.support.BasicClassTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CategoryApiMapperTest extends BasicClassTest {

    @DisplayName("[Mapper] 카테고리 도메인을 ResponseDto로 변환")
    @Test
    void givenCategoryDomain_whenTransformingDomain_thenReturnCategoryEntity() {
        //given & when
        var response = CategoryApiMapper.toRequestDto(toCategories());

        //then
        Assertions.assertInstanceOf(CategoryRequest.class, response);
    }
}