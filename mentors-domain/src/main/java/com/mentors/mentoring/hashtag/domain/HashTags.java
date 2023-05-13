package com.mentors.mentoring.hashtag.domain;

import com.mentors.mentoring.hashtag.HashTagEntity;
import com.mentors.mentoring.hashtag.MentoringHashTagEntity;
import java.util.List;
import java.util.Set;

public record HashTags(List<HashTag> hashTags) {

    public static HashTags toMentoringDto(Set<MentoringHashTagEntity> mentoringHashTagEntities) {
        return new HashTags(mentoringHashTagEntities.stream()
                .map(mentoringHashTagEntity -> HashTag.toDto(mentoringHashTagEntity.getHashTag()))
                .toList());
    }

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
