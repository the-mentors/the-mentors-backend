package com.mentors;

import static java.time.LocalDateTime.*;

import com.mentors.category.CategoryEntity;
import java.time.LocalDateTime;

public enum CategoryEntityFixture {
    PROGRAMMiNG(1L, "프로그래밍", 1000L, 0L, now(), now()),
    BACKEND(4L, "백엔드", 1100L, 1000L, now(), now()),
    FRONTEND(5L, "프론트엔드", 1200L, 1000L, now(), now()),
    AI(6L, "인공지능", 1300L, 1000L, now(), now()),
    DATA_ENGINEER(7L, "데이터엔지니어", 1400L, 1000L, now(), now()),

    EDUCATION(2L,"교육", 2000L, 0L, now(), now()),
    MATH(8L, "수학", 2100L, 2000L, now(), now()),
    ENGLISH(9L, "영어", 2200L, 2000L, now(), now()),
    SCIENCE(10L, "과학", 2300L, 2000L, now(), now()),

    INVEST(3L,"투자", 3000L, 0L, now(), now()),
    LAND(11L,"부동산", 3100L, 3000L, now(), now()),
    STOCK(12L,"주식", 3200L, 3000L, now(), now()),
    CRYPTO(13L, "암호화폐", 3300L, 3000L, now(), now());

    private final Long id;
    private final String name;
    private final Long code;
    private final Long parentCode;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    CategoryEntityFixture(final Long id,
                          final String name,
                          final Long code,
                          final Long parentCode,
                          final LocalDateTime createdAt,
                          final LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.parentCode = parentCode;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static CategoryEntity toEntity(){
        return CategoryEntity.of(PROGRAMMiNG.code, PROGRAMMiNG.name, PROGRAMMiNG.parentCode);
    }
}
