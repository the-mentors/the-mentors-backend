package com.mentors.mentoring.hashtag.mapper;

import com.mentors.mentoring.hashtag.HashTagEntity;

public class HashTagDomainMapper {

    public static HashTagEntity toEntity(final String name) {
        return new HashTagEntity(name.trim());
    }
}
