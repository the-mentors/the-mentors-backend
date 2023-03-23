package com.mentors.api.category.controller;

import static com.mentors.api.category.mapper.CategoryApiMapper.*;

import com.mentors.api.category.dto.CategoryRequest;
import com.mentors.api.category.mapper.CategoryApiMapper;
import com.mentors.api.category.usecase.GetAllCategoryUsecase;
import com.mentors.category.domain.Category;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryApiController {
    private final GetAllCategoryUsecase getAllCategoryUsecase;

    @GetMapping
    public ResponseEntity<CategoryRequest> getCategories() {
        List<Category> categories = getAllCategoryUsecase.execute();
        return ResponseEntity.ok(toRequestDto(categories));
    }
}
