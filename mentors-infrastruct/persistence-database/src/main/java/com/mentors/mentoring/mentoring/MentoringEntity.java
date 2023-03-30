package com.mentors.mentoring.mentoring;

import static jakarta.persistence.CascadeType.PERSIST;
import static jakarta.persistence.CascadeType.REMOVE;
import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

import com.mentors.global.common.BaseEntity;
import com.mentors.mentoring.category.MentoringCategoryEntity;
import com.mentors.mentoring.hashtag.MentoringHashTagEntity;
import com.mentors.user.UserEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = PROTECTED)
@Table(name = "mentoring")
public class MentoringEntity extends BaseEntity {

    @Id @GeneratedValue(strategy = IDENTITY)
    @Column(name = "mentoring_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Column(nullable = false)
    private String title;

    @Lob
    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String thumbnail;

    @Column(nullable = false)
    private Integer price;

    @Builder
    public MentoringEntity(final UserEntity user,
                           final String title,
                           final String content,
                           final String thumbnail,
                           final Integer price) {
        this.user = user;
        this.title = title;
        this.content = content;
        this.thumbnail = thumbnail;
        this.price = price;
    }

    public MentoringEntity(final Long id) {
        this.id = id;
    }
}
