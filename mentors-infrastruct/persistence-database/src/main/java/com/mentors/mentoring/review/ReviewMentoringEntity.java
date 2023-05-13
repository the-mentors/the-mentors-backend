package com.mentors.mentoring.review;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

import com.mentors.global.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "review_mentoring")
@NoArgsConstructor(access = PROTECTED)
@Getter
public class ReviewMentoringEntity extends BaseEntity {

    @Id @GeneratedValue(strategy = IDENTITY)
    @Column(name = "reivew_mentoring_id")
    private Long id;

    @Column(nullable = false, unique = true)
    private Long mentoringId;

    private double ratingAverage;
    private double totalRating;

    private int oneCount;
    private int twoCount;
    private int threeCount;
    private int fourCount;
    private int fiveCount;

    private int totalCount;

    public ReviewMentoringEntity(Long mentoringId) {
        this.mentoringId = mentoringId;
    }

    public synchronized void rate(Rating rating) {
        switch (rating) {
            case ONE -> oneCount++;
            case TWO -> twoCount++;
            case THREE -> threeCount++;
            case FOUR -> fourCount++;
            case FIVE -> fiveCount++;
            default -> throw new IllegalArgumentException("별점에 없는 수치 입니다.");
        }

        totalCount++;
        this.totalRating += rating.getValue();
        this.ratingAverage = totalRating / totalCount;
    }
}
