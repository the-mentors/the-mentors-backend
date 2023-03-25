package com.mentors.api.category.dto;

import com.mentors.category.domain.Category;
import java.util.List;

public record CategoryRequest(List<Category> categories) {
}
