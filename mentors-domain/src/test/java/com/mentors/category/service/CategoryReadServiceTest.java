package com.mentors.category.service;

import static com.mentors.CategoryFixture.toCategories;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertAll;

import com.mentors.category.CategoryEntity;
import com.mentors.category.CategoryRepository;
import com.mentors.category.domain.Category;
import com.mentors.support.ServiceTest;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


class CategoryReadServiceTest extends ServiceTest {

    @Autowired
    private CategoryReadService categoryReadService;

    @Autowired
    private CategoryRepository categoryRepository;

    @BeforeEach
    void initialize(){
        categoryRepository.saveAll(toCategoryEntities());
    }

    @DisplayName("카테고리 조회 조회 성공")
    @Test
    void givenNoParameters_whenSearchingCategories_thenReturnCategoryDomainList() {
        //when
        List<Category> actual = categoryReadService.getCategories();

        //then
        assertAll(() ->{
            assertThat(actual.size()).isEqualTo(3);
            assertThat(actual.get(0).code()).isEqualTo(1000L);
            assertThat(actual.get(1).code()).isEqualTo(2000L);
            assertThat(actual.get(2).code()).isEqualTo(3000L);
        });
    }

    @DisplayName("존재하지 않는 코드로 조회할 경우, 예외를 던진다.")
    @Test
    void givenNonexistentCategoryCode_whenSearchingCategoryCode_thenThrowsException(){
        //given
        Long nonExistentCode = 0L;

        //when & then
        assertThatCode(() -> categoryReadService.existCategory(nonExistentCode))
                .isInstanceOf(RuntimeException.class);
    }

    @DisplayName("존재하는 코드로 조회시, 해당 카테고리를 반환한다.")
    @Test
    void givenExistentCategoryCode_whenSearchingCategoryCode_thenReturnCategoryCode(){
        //given
        Long existentCode = 1000L;

        assertThatCode(() -> categoryReadService.existCategory(existentCode))
                .doesNotThrowAnyException();
    }

    private static List<CategoryEntity> toCategoryEntities(){
        return toCategories().stream()
                .map(c -> CategoryEntity.of(c.code(), c.name(), c.parentCode()))
                .collect(Collectors.toList());
    }
}