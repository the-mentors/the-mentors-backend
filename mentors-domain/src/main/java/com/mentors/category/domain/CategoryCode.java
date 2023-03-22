package com.mentors.category.domain;

import java.util.Arrays;

public enum CategoryCode {
    PROGRAMMiNG("프로그래밍", 1000L),
    BACKEND("백엔드", 1100L),
    FRONTEND("프론트엔드", 1200L),
    AI("인공지능", 1300L),
    DATA_ENGINEER("데이터엔지니어", 1400L),

    EDUCATION("교육", 2000L),
    MATH("수학", 2100L),
    ENGLISH("영어", 2200L),
    SCIENCE("과학", 2300L),

    INVEST("투자", 3000L),
    LAND("부동산", 3100L),
    STOCK("주식", 3200L),
    CRYPTO("암호화폐", 3300L);


    private final String name;
    private final Long code;

    CategoryCode(final String name, final Long code) {
        this.name = name;
        this.code = code;
    }

    public static CategoryCode findCategoryCode(Long code) {
        return Arrays.stream(values())
                .filter(e -> e.hasCategoryCode(code))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException());
    }

    public boolean hasCategoryCode(Long code) {
        return this.code.equals(code);
    }
}
