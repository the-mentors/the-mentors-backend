package com.mentors.mentoring.mentoring.dto;

import com.mentors.mentoring.hashtag.HashTagEntity;
import com.mentors.mentoring.hashtag.domain.HashTags;
import com.mentors.mentoring.mentoring.MentoringEntity;
import com.mentors.user.user.dto.UserResponse;
import java.util.List;

public record MentoringSingleResponse(Long id,
                                      UserResponse userResponse,
                                      String title,
                                      String content,
                                      String thumbnail,
                                      Integer price,
                                      double rating,
                                      boolean isOwner,
                                      HashTags hashTags) {

    public static MentoringSingleResponse toDto(final MentoringEntity mentoring,final boolean isOwner, final List<HashTagEntity> hashTags) {
        return new MentoringSingleResponse(
                mentoring.getId(),
                UserResponse.toDto(mentoring.getUser()),
                mentoring.getTitle(),
                mentoring.getContent(),
                mentoring.getThumbnail(),
                mentoring.getPrice(),
                mentoring.getRating(),
                isOwner,
                HashTags.toDto(hashTags));
    }

}
