package com.mentors.api.user.dto;

import jakarta.validation.constraints.Size;
import org.springframework.lang.Nullable;

public record UserEditRequest(
        @Nullable String userName,
        @Size(min = 2, max = 14) String nickName,
        @Nullable String profileUrl
) {
}
