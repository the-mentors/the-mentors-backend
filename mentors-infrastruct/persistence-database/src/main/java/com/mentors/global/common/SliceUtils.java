package com.mentors.global.common;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SliceUtils<T> {

    public boolean hasNext(Pageable pageable, List<T> content) {
        boolean hasNext = false;
        if (content.size() > pageable.getPageSize()) {
            content.remove(pageable.getPageSize());
            hasNext = true;
        }
        return hasNext;
    }

}
