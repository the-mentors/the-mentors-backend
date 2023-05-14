package com.mentors.mentoring.mentoring;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Lob;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Content {

    private static final int MAX_CONTENT_LENGTH = 5000;

    @Lob
    @Column(name = "content", length = 5000, nullable = false)
    private String value;


    public Content(final String value) {
        validate(value);
        this.value = value;
    }

    private void validate(final String value) {
        if (!StringUtils.hasText(value)) {
            throw new RuntimeException();
        }
        if (value.length() > MAX_CONTENT_LENGTH) {
            throw new RuntimeException();
        }
    }
}
