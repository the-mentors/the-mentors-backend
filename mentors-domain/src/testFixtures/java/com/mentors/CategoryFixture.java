package com.mentors;

import static java.time.LocalDateTime.now;

import com.mentors.category.domain.Category;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public enum CategoryFixture {

    PROGRAMMiNG("프로그래밍", 1000L, 0L),
    BACKEND("백엔드", 1100L, 1000L),
    FRONTEND("프론트엔드", 1200L, 1000L),
    AI("인공지능", 1300L, 1000L),
    DATA_ENGINEER("데이터엔지니어", 1400L, 1000L),

    EDUCATION("교육", 2000L, 0L),
    MATH("수학", 2100L, 2000L),
    ENGLISH("영어", 2200L, 2000L),
    SCIENCE("과학", 2300L, 2000L),

    INVEST("투자", 3000L, 0L),
    LAND("부동산", 3100L, 3000L),
    STOCK("주식", 3200L, 3000L),
    CRYPTO("암호화폐", 3300L, 3000L);


    private final String name;
    private final Long code;
    private final Long parentCode;

    CategoryFixture(String name, Long code, Long parentCode) {
        this.name = name;
        this.code = code;
        this.parentCode = parentCode;
    }

    public static Category toDomain(){
        return Category.of(null, PROGRAMMiNG.name, PROGRAMMiNG.code, PROGRAMMiNG.parentCode, null, null);
    }

    public static List<Category> toCategories() {
        return Arrays.stream(values())
                .map(c -> Category.of(null, c.name, c.code, c.parentCode, now(), now()))
                .collect(Collectors.toList());
    }


    public static List<Category> toSortCategories(){
        Map<Long, List<Category>> categoriesGroupingByParentId = toCategories().stream()
                .collect(Collectors.groupingBy(Category::parentCode));

        List<Category> rootCategories = categoriesGroupingByParentId.get(0L);
        rootCategories.forEach(root -> addRecursionSubcategories(root, categoriesGroupingByParentId));

        return rootCategories;
    }
    private static void addRecursionSubcategories(Category parent,
                                                  Map<Long, List<Category>> categoriesGroupingByParentId){
        List<Category> subCategories = categoriesGroupingByParentId.get(parent.code());

        if (Objects.isNull(subCategories)) return;

        parent.addSubCategories(subCategories);
        subCategories.forEach(s -> addRecursionSubcategories(s, categoriesGroupingByParentId));
    }
}
