package com.mentors.category.service;

import com.mentors.category.domain.Category;
import com.mentors.category.domain.CategoryCode;
import java.util.List;

public interface CategoryReadService {

    List<Category> getCategories();
    CategoryCode existCategory(Long code);
}
