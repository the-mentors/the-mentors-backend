package com.mentors.mentoring.hashtag;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

import com.mentors.global.common.BaseEntity;
import com.mentors.mentoring.mentoring.MentoringEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = PROTECTED)
@Table(name = "mentoring_hashtags")
public class MentoringHashTagEntity extends BaseEntity {

    @Id @GeneratedValue(strategy = IDENTITY)
    @Column(name = "mentoring_hashtag_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "mentoring_id", nullable = false)
    private MentoringEntity mentoring;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "hashtag_id", nullable = false)
    private HashTagEntity hashTag;


    public MentoringHashTagEntity(final MentoringEntity mentoring,
                                  final HashTagEntity hashTag) {
        this.mentoring = mentoring;
        this.hashTag = hashTag;
    }

    public static MentoringHashTagEntity of(final Long hashTagId){
        return new MentoringHashTagEntity(null, HashTagEntity.of(hashTagId));
    }

    public void addMentoring(final MentoringEntity mentoring){
        this.mentoring = mentoring;
    }
}
