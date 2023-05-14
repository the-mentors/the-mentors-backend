package com.mentors.user.user.dto;

import jakarta.validation.constraints.Size;
import org.springframework.lang.Nullable;

public record UserEditRequest(
        @Nullable String username,
        @Size(min = 2, max = 14) String nickname,
        @Nullable String profileUrl
) {
}
