package com.mentors.mentoring.mentoring;

import static org.assertj.core.api.Assertions.assertThatCode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TitleTest {

    @DisplayName("제목 변수가 null 이거나 비어있을 경우 예외가 발생한다.")
    @Test
    void givenTitleIsNullOrNoneString_whenConstruct_thenReturnThrows() {
        //given
        final String nullTitle = null;
        final String noneStringTitle = "";

        //when & then
        assertThatCode(() -> new Title(nullTitle))
                .isInstanceOf(RuntimeException.class);
        assertThatCode(() -> new Title(noneStringTitle))
                .isInstanceOf(RuntimeException.class);
    }


    @DisplayName("제목이 50글자 초과일 경우 예외가 발생한다.")
    @Test
    void givenTitle_whenConstruct_thenReturnThrows() {
        //given
        final String title1 = "*".repeat(51);

        //when & then
        assertThatCode(() -> new Title(title1))
                .isInstanceOf(RuntimeException.class);
    }

    @DisplayName("제목이 1자 이상 50글자 이하인 경우 정상적으로 Title 객체가 생성된다.")
    @Test
    void givenTitle_whenConstruct_thenReturnTitle() {
        //given
        final String title1 = "*".repeat(1);
        final String title2 = "*".repeat(50);

        //when & then
        assertThatCode(() -> new Title(title1)).doesNotThrowAnyException();
        assertThatCode(() -> new Title(title2)).doesNotThrowAnyException();
    }
}