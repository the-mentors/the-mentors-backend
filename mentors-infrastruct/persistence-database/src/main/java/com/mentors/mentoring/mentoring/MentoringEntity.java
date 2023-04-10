package com.mentors.mentoring.mentoring;

import static com.mentors.category.CategoryEntity.*;
import static jakarta.persistence.CascadeType.PERSIST;
import static jakarta.persistence.CascadeType.REMOVE;
import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

import com.mentors.category.CategoryEntity;
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

    @Column(nullable = false)
    private Long userId;

    @Embedded
    private Title title;

    @Embedded
    private Content content;

    @Column(nullable = false)
    private String thumbnail;

    @Embedded
    private Price price;

    @OneToMany(mappedBy = "mentoring", cascade = {PERSIST, REMOVE})
    private List<MentoringCategoryEntity> categories = new ArrayList<>();

    @OneToMany(mappedBy = "mentoring", cascade = {PERSIST, REMOVE})
    private Set<MentoringHashTagEntity> hashTags = new HashSet<>();

    @OneToMany(mappedBy = "mentoring", cascade = {PERSIST, REMOVE})
    private List<MentoringLinkEntity> links = new ArrayList<>();

    @Builder
    public MentoringEntity(final Long userId,
                           final String title,
                           final String content,
                           final String thumbnail,
                           final Integer price) {
        this.userId = userId;
        this.title = new Title(title);
        this.content = new Content(content);
        this.thumbnail = thumbnail;
        this.price = new Price(price);
    }

    public void addMentoringCategories(final List<MentoringCategoryEntity> mentoringCategoryEntities){
        for (final MentoringCategoryEntity entity : mentoringCategoryEntities) {
            this.addMentoringCategory(entity);
        }
    }

    private void addMentoringCategory(final MentoringCategoryEntity entity){
        categories.add(entity);
        entity.addMentoring(this);
    }

    public void addMentoringHashTags(final Set<Long> hashTagIds){
        for (final Long hashTagId : hashTagIds) {
            this.addMentoringHasTag(MentoringHashTagEntity.of(hashTagId));
        }
    }

    private void addMentoringHasTag(final MentoringHashTagEntity mentoringHashTagEntity){
        hashTags.add(mentoringHashTagEntity);
        mentoringHashTagEntity.addMentoring(this);
    }

    public void addMentoringLinks(final List<MentoringLinkEntity> mentoringLinkEntities){
        for (final MentoringLinkEntity entity : mentoringLinkEntities) {
            this.addMentoringLink(entity);
        }
    }

    private void addMentoringLink(final MentoringLinkEntity mentoringLinkEntity){
        links.add(mentoringLinkEntity);
        mentoringLinkEntity.addMentoring(this);
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
