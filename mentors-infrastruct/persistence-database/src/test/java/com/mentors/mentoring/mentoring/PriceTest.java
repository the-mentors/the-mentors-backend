package com.mentors.mentoring.mentoring;

import static org.assertj.core.api.Assertions.assertThatCode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PriceTest {

    @DisplayName("가격 변수가 null일 경우 예외가 발생한다.")
    @Test
    void givenPriceIsNull_whenConstruct_thenReturnThrows() {
        //given
        final Integer nullPrice = null;

        //when & then
        assertThatCode(() -> new Price(nullPrice))
                .isInstanceOf(RuntimeException.class);
    }


    @DisplayName("가격 변수가 1천만을 초과하거나 0보다 작을 경우 예외가 발생한다.")
    @Test
    void givenPrice_whenConstruct_thenReturnThrows() {
        //given
        final Integer maxOverPrice = 10_000_001;
        final Integer zeroDownPrice = -1;

        //when & then
        assertThatCode(() -> new Price(maxOverPrice)).isInstanceOf(RuntimeException.class);
        assertThatCode(() -> new Price(zeroDownPrice)).isInstanceOf(RuntimeException.class);
    }

    @DisplayName("가격 변수가 0 이상이거나 1천만 이하일 경우 Price 객체가 생성된다.")
    @Test
    void givenPrice_whenConstruct_thenReturnPrice() {
        //given
        final Integer price1 = 10_000_000;
        final Integer price2 = 0;

        //when & then
        assertThatCode(() -> new Price(price1)).doesNotThrowAnyException();
        assertThatCode(() -> new Price(price2)).doesNotThrowAnyException();
    }
}