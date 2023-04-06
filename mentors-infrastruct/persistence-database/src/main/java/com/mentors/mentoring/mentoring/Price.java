package com.mentors.mentoring.mentoring;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.util.Objects;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Price {
    private static final Integer MAX_VALUE = 10_000_000;

    @Column(name = "price", nullable = false)
    private Integer value;


    public Price(final Integer value) {
        validate(value);
        this.value = value;
    }

    private void validate(final Integer value) {
        if (value == null) {
            throw new RuntimeException();
        }
        if (value > MAX_VALUE || value < 0) {
            throw new RuntimeException();
        }
    }

}
