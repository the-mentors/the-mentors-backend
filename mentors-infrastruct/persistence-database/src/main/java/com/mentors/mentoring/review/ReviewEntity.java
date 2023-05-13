package com.mentors.mentoring.review;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

import com.mentors.global.common.BaseEntity;
import com.mentors.mentoring.mentoring.MentoringEntity;
import com.mentors.user.user.UserEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = PROTECTED)
@Table(name = "reviews")
public class ReviewEntity extends BaseEntity {


    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "reviews_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reviewer_id", nullable = false)
    private UserEntity reviewer;

    @Column(nullable = false)
    private Long mentoringId;

    @Embedded
    private ReviewContent reviewContent;

    @Builder
    public ReviewEntity(Long id, UserEntity reviewer, Long mentoringId, ReviewContent reviewContent) {
        this.id = id;
        this.reviewer = reviewer;
        this.mentoringId = mentoringId;
        this.reviewContent = reviewContent;
    }

    public static ReviewEntity of(UserEntity reviewer, Long mentoringId, ReviewContent reviewContent) {
        return new ReviewEntity(null, reviewer, mentoringId, reviewContent);
    }
}
