package com.mentors.mentoring.mentoring;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Title {
    private static final int MAX_TITLE_LENGTH = 50;

    @Column(name = "title", length = 50, nullable = false)
    private String value;


    public Title(final String value) {
        validate(value);
        this.value = value;
    }

    private void validate(final String value) {
        if (!StringUtils.hasText(value)) {
            throw new RuntimeException();
        }
        if (value.length() > MAX_TITLE_LENGTH) {
            throw new RuntimeException();
        }
    }

}
