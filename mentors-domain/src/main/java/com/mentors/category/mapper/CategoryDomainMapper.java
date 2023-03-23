package com.mentors.category.mapper;

import com.mentors.category.CategoryEntity;
import com.mentors.category.domain.Category;

public class CategoryDomainMapper {

    public static Category toDomain(CategoryEntity ce){
        return Category.of(ce.getId(),
                ce.getCategoryName(),
                ce.getCategoryCode(),
                ce.getParentCode(),
                ce.getCreatedAt(),
                ce.getUpdatedAt());
    }

    public static CategoryEntity toEntity(Category cd){
        return CategoryEntity.of(cd.code(), cd.name(), cd.parentCode());
    }
}
