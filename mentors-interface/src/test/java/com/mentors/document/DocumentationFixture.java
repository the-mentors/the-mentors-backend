package com.mentors.document;

import com.mentors.CategoryFixture;
import com.mentors.api.category.dto.CategoryRequest;

public class DocumentationFixture {

    public static CategoryRequest CATEGORY_RESPONSE = new CategoryRequest(CategoryFixture.toCategories());
}
