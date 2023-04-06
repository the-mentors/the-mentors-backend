package com.mentors.mentoring.hashtag;

import static lombok.AccessLevel.PROTECTED;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@NoArgsConstructor(access = PROTECTED)
public class HashTagName {

    private static final int MAX_TITLE_LENGTH = 15;

    @Column(name = "hashtag_name", length = 15, nullable = false)
    private String value;


    public HashTagName(final String value) {
        validate(value);
        this.value = value;
    }

    private void validate(final String value) {
        if (value == null || value.isBlank()) {
            throw new RuntimeException();
        }
        if (value.length() > MAX_TITLE_LENGTH) {
            throw new RuntimeException();
        }
    }
}
