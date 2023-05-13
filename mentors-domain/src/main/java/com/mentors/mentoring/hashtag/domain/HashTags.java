package com.mentors.mentoring.hashtag.domain;

import com.mentors.mentoring.hashtag.HashTagEntity;
import java.util.List;

public record HashTags(List<HashTag> hashTags) {

    public static HashTags toDto(List<HashTagEntity> hashTagEntities) {
        return new HashTags(hashTagEntities.stream()
                .map(HashTag::toDto)
                .toList());
    }
    record HashTag(Long id, String name) {
        public static HashTag toDto(HashTagEntity hashTag) {
            return new HashTag(hashTag.getId(), hashTag.getName());
        }
    }

}
