package com.mentors.mentoring.review.dto;


import com.mentors.mentoring.review.ReviewMentoringEntity;
public record ReviewStatisticResponse(Long id,
                                      double ratingAverage,
                                      int oneCount,
                                      int twoCount,
                                      int threeCount,
                                      int fourCount,
                                      int fiveCount,
                                      int totalCount) {

    public static ReviewStatisticResponse toDto(ReviewMentoringEntity reviewMentoring) {
        return new ReviewStatisticResponse(
                reviewMentoring.getId(),
                reviewMentoring.getRatingAverage(),
                reviewMentoring.getOneCount(),
                reviewMentoring.getTwoCount(),
                reviewMentoring.getThreeCount(),
                reviewMentoring.getFourCount(),
                reviewMentoring.getFiveCount(),
                reviewMentoring.getTotalCount()
        );
    }
}
