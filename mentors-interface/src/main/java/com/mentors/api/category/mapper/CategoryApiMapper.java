package com.mentors.api.category.mapper;

import com.mentors.api.category.dto.CategoryRequest;
import com.mentors.category.domain.Category;
import java.util.List;

public class CategoryApiMapper {

    public static CategoryRequest toRequestDto(List<Category> categories){
        return new CategoryRequest(categories);
    }
}
