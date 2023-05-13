package com.mentors.utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

public class PageUtils {

    public static PageRequest defaultPage(Pageable pageable) {
        return PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), orderBy(pageable.getSort()));
    }

    private static Sort orderBy(Sort sort){
        if (sort.isEmpty()){
            return Sort.by(Direction.DESC, "id");
        }
        return sort;
    }
}
