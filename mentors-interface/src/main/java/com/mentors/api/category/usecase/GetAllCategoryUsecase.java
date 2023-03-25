package com.mentors.api.category.usecase;

import com.mentors.category.domain.Category;
import com.mentors.category.service.CategoryReadService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class GetAllCategoryUsecase {
    private final CategoryReadService categoryReadService;

    public List<Category> execute() {
        log.info("Request : {} ", "CategoryReadService.getAllCategories()");
        return categoryReadService.getCategories();
    }
}
