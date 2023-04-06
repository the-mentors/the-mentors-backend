package com.mentors.mentoring.hashtag;

import static org.assertj.core.api.Assertions.assertThatCode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HashTagNameTest {

    @DisplayName("해시태그명 변수가 null 이거나 비어있을 경우 예외가 발생한다.")
    @Test
    void givenHashTagNameIsNullOrNoneString_whenConstruct_thenReturnThrows() {
        //given
        final String nullHashTagName = null;
        final String noneStringHashTagName = "";

        //when & then
        assertThatCode(() -> new HashTagName(nullHashTagName))
                .isInstanceOf(RuntimeException.class);
        assertThatCode(() -> new HashTagName(noneStringHashTagName))
                .isInstanceOf(RuntimeException.class);
    }


    @DisplayName("해시태그명 변수가 15글자 초과일 경우 예외가 발생한다.")
    @Test
    void givenHashTagName_whenConstruct_thenReturnThrows() {
        //given
        final String hashTagName = "*".repeat(16);

        //when & then
        assertThatCode(() -> new HashTagName(hashTagName))
                .isInstanceOf(RuntimeException.class);
    }

    @DisplayName("해시태그명 변수가 1자 이상 15글자 이하인 경우 정상적으로 HashTagNane 객체가 생성된다.")
    @Test
    void givenHashTagName_whenConstruct_thenReturnHashTagName() {
        //given
        final String hashTagName1 = "*".repeat(1);
        final String hashTagName2 = "*".repeat(15);

        //when & then
        assertThatCode(() -> new HashTagName(hashTagName1)).doesNotThrowAnyException();
        assertThatCode(() -> new HashTagName(hashTagName2)).doesNotThrowAnyException();
    }
}