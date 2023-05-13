package com.mentors.mentoring.dto;

import com.mentors.mentoring.hashtag.domain.HashTags;
import com.mentors.mentoring.mentoring.MentoringEntity;
import com.mentors.user.dto.UserResponse;

public record MentoringSingleResponse(Long id,
                                      UserResponse userResponse,
                                      String title,
                                      String content,
                                      String thumbnail,
                                      Integer price,
                                      HashTags hashTags) {

    public static MentoringSingleResponse toDto(final MentoringEntity mentoring) {
        return new MentoringSingleResponse(
                mentoring.getId(),
                UserResponse.toDto(mentoring.getUser()),
                mentoring.getTitle(),
                mentoring.getContent(),
                mentoring.getThumbnail(),
                mentoring.getPrice(),
                HashTags.toMentoringDto(mentoring.getHashTags()));
    }

}
