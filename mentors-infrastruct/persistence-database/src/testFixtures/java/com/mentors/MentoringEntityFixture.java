package com.mentors;

import com.mentors.mentoring.mentoring.MentoringEntity;

public enum MentoringEntityFixture {

    멘토링_등록("제목", "내용", 1000, "썸네일 URL"),
    ;

    private final String title;
    private final String cotent;
    private final Integer price;
    private final String thumbnail;

    MentoringEntityFixture(String title, String cotent, Integer price, String thumbnail) {
        this.title = title;
        this.cotent = cotent;
        this.price = price;
        this.thumbnail = thumbnail;
    }

    public static MentoringEntity 멘토링_등록(final Long userId){
        return new MentoringEntity(userId, 멘토링_등록.title, 멘토링_등록.cotent, 멘토링_등록.thumbnail, 멘토링_등록.price);
    }
}
