package com.mentors.mentoring.review.dto;

import java.util.List;

public record ReviewResponses(List<ReviewResponse> content, boolean hasNext) {
}
