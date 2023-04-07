package com.mentors.mentoring.mentoring;

import static jakarta.persistence.CascadeType.PERSIST;
import static jakarta.persistence.CascadeType.REMOVE;
import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

import com.mentors.global.common.BaseEntity;
import com.mentors.mentoring.category.MentoringCategoryEntity;
import com.mentors.mentoring.hashtag.MentoringHashTagEntity;
import com.mentors.user.user.UserEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
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

    @Embedded
    private Title title;

    @Embedded
    private Content content;

    @Column(nullable = false)
    private String thumbnail;

    @Embedded
    private Price price;

    @OneToMany(mappedBy = "mentoring", cascade = { REMOVE })
    private List<MentoringCategoryEntity> categories = new ArrayList<>();

    @OneToMany(mappedBy = "mentoring", cascade = REMOVE)
    private Set<MentoringHashTagEntity> hashTags = new HashSet<>();

    @OneToMany(mappedBy = "mentoring", cascade = {PERSIST, REMOVE})
    private List<MentoringLinkEntity> links = new ArrayList<>();

    @Builder
    public MentoringEntity(final UserEntity user,
                           final Title title,
                           final Content content,
                           final String thumbnail,
                           final Price price,
                           final List<MentoringCategoryEntity> categories,
                           final Set<MentoringHashTagEntity> hashTags,
                           final List<MentoringLinkEntity> links) {
        this.user = user;
        this.title = title;
        this.content = content;
        this.thumbnail = thumbnail;
        this.price = price;
        this.categories = categories;
        this.hashTags = hashTags;
        this.links = links;
    }

    public MentoringEntity(final Long id) {
        this.id = id;
    }

    public String getTitle(){
        return title.getValue();
    }

    public Integer getPrice(){
        return price.getValue();
    }

    public String getContent(){
        return content.getValue();
    }
}
