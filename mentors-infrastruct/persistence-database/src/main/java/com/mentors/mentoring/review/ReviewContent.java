package com.mentors.mentoring.review;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Lob;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;


@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReviewContent {

    private static final int MAX_CONTENT_LENGTH = 500;

    @Enumerated(EnumType.STRING)
    private Rating rating;
    @Lob
    @Column(nullable = false)
    private String content;

    public ReviewContent(Rating rating, String content) {
        validate(content);
        this.rating = rating;
        this.content = content;
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
