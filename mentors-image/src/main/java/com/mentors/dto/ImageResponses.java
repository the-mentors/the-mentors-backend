package com.mentors.dto;

import java.util.List;

public record ImageResponses(List<ImageResponse> responses) {
    public static ImageResponses of(List<ImageResponse> responses) {
        return new ImageResponses(responses);
    }
}
