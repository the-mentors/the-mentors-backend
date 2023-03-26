package com.mentors.authToken.domain;

import java.time.LocalDateTime;

public record AuthToken(Long memberId,
                        String refreshToken,
                        LocalDateTime createdAt,
                        LocalDateTime updatedAt) {
}
