package com.mentors.mentoring.mentoring;

import static org.assertj.core.api.Assertions.assertThatCode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ContentTest {

    @DisplayName("글 변수가 null 이거나 비어있을 경우 예외가 발생한다.")
    @Test
    void givenContentIsNullOrNoneString_whenConstruct_thenReturnThrows() {
        //given
        final String nullContent = null;
        final String noneStringContent = "";
        final String gapNoneStringContent = " ";

        //when & then
        assertThatCode(() -> new Content(nullContent)).isInstanceOf(RuntimeException.class);
        assertThatCode(() -> new Title(noneStringContent)).isInstanceOf(RuntimeException.class);
        assertThatCode(() -> new Title(gapNoneStringContent)).isInstanceOf(RuntimeException.class);
    }


    @DisplayName("글 변수가 5천자를 초과일 경우 예외가 발생한다.")
    @Test
    void givenContent_whenConstruct_thenReturnThrows() {
        //given
        final String content = "*".repeat(5001);

        //when & then
        assertThatCode(() -> new Content(content))
                .isInstanceOf(RuntimeException.class);
    }

    @DisplayName("글 변수 1자 이상 5000글자 이하인 경우 정상적으로 Centent 객체가 생성된다.")
    @Test
    void givenContent_whenConstruct_thenReturnContent() {
        //given
        final String content1 = "*".repeat(1);
        final String content2 = "*".repeat(5000);

        //when & then
        assertThatCode(() -> new Content(content1)).doesNotThrowAnyException();
        assertThatCode(() -> new Content(content2)).doesNotThrowAnyException();
    }
}