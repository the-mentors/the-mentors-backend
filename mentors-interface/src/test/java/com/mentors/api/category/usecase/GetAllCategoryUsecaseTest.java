package com.mentors.api.category.usecase;

import static com.mentors.CategoryFixture.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

import com.mentors.CategoryFixture;
import com.mentors.category.domain.Category;
import com.mentors.category.service.CategoryReadService;
import com.mentors.support.UsecaseTest;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

class GetAllCategoryUsecaseTest extends UsecaseTest {

    @InjectMocks
    private GetAllCategoryUsecase getAllCategoryUsecase;

    @Mock
    private CategoryReadService categoryReadService;

    @DisplayName("[Usecase] 카테고리 전체 조회")
    @Test
    void givenNoParameters_whenSearchingCategories_thenReturnCategoryDomainList() {
        //given
        given(categoryReadService.getCategories()).willReturn(toSortCategories());

        //when
        var actual = getAllCategoryUsecase.execute();

        //then
        assertAll(() ->{
            assertThat(actual.size()).isEqualTo(3);
            assertThat(actual.get(0).code()).isEqualTo(1000L);
            assertThat(actual.get(1).code()).isEqualTo(2000L);
            assertThat(actual.get(2).code()).isEqualTo(3000L);
        });
    }
}