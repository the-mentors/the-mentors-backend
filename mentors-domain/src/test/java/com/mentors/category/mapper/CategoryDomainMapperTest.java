package com.mentors.category.mapper;

import static com.mentors.CategoryFixture.*;

import com.mentors.category.CategoryEntity;
import com.mentors.category.domain.Category;
import com.mentors.support.BasicClassTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CategoryDomainMapperTest extends BasicClassTest {

    @DisplayName("카테고리 도메인을 엔티티로 변환")
    @Test
    void givenCategoryDomain_whenTransformingDomain_thenReturnCategoryEntity() {
        //given & when
        CategoryEntity entity = CategoryDomainMapper.toEntity(toDomain());

        //then
        Assertions.assertInstanceOf(CategoryEntity.class, entity);
    }

    @DisplayName("카테고리 엔티티를 도메인으로 변환")
    @Test
    void givenCategoryEntity_whenTransformingEntity_thenReturnCategoryDomain() {
        //given & when
        Category domain = CategoryDomainMapper.toDomain(CategoryEntity.of(1000L, "프로그래밍", 0L));

        //then
        Assertions.assertInstanceOf(Category.class, domain);
    }
}
