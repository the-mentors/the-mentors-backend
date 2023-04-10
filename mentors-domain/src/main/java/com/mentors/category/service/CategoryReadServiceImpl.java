package com.mentors.category.service;

import static java.util.stream.Collectors.groupingBy;

import com.mentors.category.CategoryRepository;
import com.mentors.category.domain.Category;
import com.mentors.category.domain.CategoryCode;
import com.mentors.category.mapper.CategoryDomainMapper;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryReadServiceImpl implements CategoryReadService {

    private final CategoryRepository categoryRepository;
    private static final Long ROOT = 0L;

    @Override
    public List<Category> getCategories() {
        var categoriesGroupingByParentId = categoryRepository.findAll().stream()
                .map(CategoryDomainMapper::toDomain)
                .collect(groupingBy(Category::parentCode));

        if(categoriesGroupingByParentId.isEmpty()) return Collections.emptyList();

        List<Category> rootCategories = categoriesGroupingByParentId.get(ROOT);
        rootCategories.forEach(root -> addRecursionSubcategories(root, categoriesGroupingByParentId));

        return rootCategories;
    }

    private void addRecursionSubcategories(Category parent,
                                           Map<Long, List<Category>> categoriesGroupingByParentId){
        List<Category> subCategories = categoriesGroupingByParentId.get(parent.code());

        if (Objects.isNull(subCategories)) return;

        parent.addSubCategories(subCategories);
        subCategories.forEach(s -> addRecursionSubcategories(s, categoriesGroupingByParentId));
    }

    @Override
    public CategoryCode existCategory(Long code) {
        return CategoryCode.findCategoryCode(code);
    }

    @Override
    public Long findCategoryIdByCode(final Long code) {
        return categoryRepository.findByCategoryCode(code)
                .orElseThrow()
                .getId();
    }
}
