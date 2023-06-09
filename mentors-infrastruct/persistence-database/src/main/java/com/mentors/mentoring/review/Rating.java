package com.mentors.mentoring.review;

import java.util.Arrays;
import lombok.Getter;

@Getter
public enum Rating {
    ONE(1), TWO(2), THREE(3), FOUR(4), FIVE(5);

    private int value;

    Rating(int value) {
        this.value = value;
    }

    public static Rating valueOf(int rating) {
        return Arrays.stream(values())
                .filter(r -> r.getValue() == rating)
                .findFirst()
                .orElseThrow();
    }
}
