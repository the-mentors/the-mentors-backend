package com.mentors.common;

import java.util.List;

public record PageResponse<T>(List<T> content,
                              int totalPages,
                              long totalElements,
                              int size,
                              boolean last) {
}

