package com.mentors.auth.domain;

import java.time.LocalDateTime;

public record Auth(Long memberId,
                   String refreshToken,
                   LocalDateTime createdAt,
                   LocalDateTime updatedAt) {
}
