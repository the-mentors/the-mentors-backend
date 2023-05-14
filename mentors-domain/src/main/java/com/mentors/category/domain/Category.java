package com.mentors.category.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public record Category(Long id,
                       String name,
                       Long code,
                       Long parentCode,
                       LocalDateTime createdAt,
                       LocalDateTime updatedAt,
                       List<Category> subCategories) {

    public static Category of(final Long id,
                              final String name,
                              final Long code,
                              final Long parentCode,
                              final LocalDateTime createdAt,
                              final LocalDateTime updatedAt){
        return new Category(id, name, code, parentCode, createdAt, updatedAt, new ArrayList<>());
    }

    public void addSubCategories(final List<Category> subCategories){
        this.subCategories.addAll(subCategories);
    }
}
