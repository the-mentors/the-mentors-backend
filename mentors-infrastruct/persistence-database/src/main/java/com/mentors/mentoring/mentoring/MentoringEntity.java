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
import com.mentors.mentoring.hashtag.HashTagEntity;
import com.mentors.mentoring.hashtag.MentoringHashTagEntity;
import com.mentors.user.user.UserEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
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
import java.util.Objects;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @Embedded
    private Title title;

    @Embedded
    private Content content;

    @Column(nullable = false)
    private String thumbnail;

    @Embedded
    private Price price;

    @OneToMany(mappedBy = "mentoring", cascade = {PERSIST, REMOVE})
    private final List<MentoringCategoryEntity> categories = new ArrayList<>();

    @OneToMany(mappedBy = "mentoring", cascade = {PERSIST, REMOVE})
    private final Set<MentoringHashTagEntity> hashTags = new HashSet<>();

    @OneToMany(mappedBy = "mentoring", cascade = {PERSIST, REMOVE})
    private final List<MentoringLinkEntity> links = new ArrayList<>();

    @Builder
    public MentoringEntity(final UserEntity user,
                           final String title,
                           final String content,
                           final String thumbnail,
                           final Integer price) {
        this.user = user;
        this.title = new Title(title);
        this.content = new Content(content);
        this.thumbnail = thumbnail;
        this.price = new Price(price);
    }

    public boolean isOwner(final Long ownerId){
        return user.getId().equals(ownerId);
    }

    public void addMentoringCategories(final List<CategoryEntity> categories){
        for (final CategoryEntity category : categories) {
            this.addMentoringCategory(MentoringCategoryEntity.of(this, category));
        }
    }

    private void addMentoringCategory(final MentoringCategoryEntity entity){
        categories.add(entity);
    }

    public void addMentoringHashTags(final Set<HashTagEntity> hashTags){
        for (final HashTagEntity hashTag : hashTags) {
            addMentoringHasTag(MentoringHashTagEntity.of(this, hashTag));
        }
    }

    private void addMentoringHasTag(final MentoringHashTagEntity mentoringHashTagEntity) {
        hashTags.add(mentoringHashTagEntity);
    }

    public void addMentoringLinks(final List<MentoringLinkEntity> mentoringLinks){
        for (final MentoringLinkEntity entity : mentoringLinks) {
            this.addMentoringLink(entity);
        }
    }

    private void addMentoringLink(final MentoringLinkEntity entity){
        entity.addMentoring(this);
        links.add(entity);
    }

    public UserEntity getUser() {
        return user;
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

    public List<Long> getHashTagIds(){
        return hashTags.stream()
                .map(hashTag -> hashTag.getHashTag().getId())
                .toList();
    }

    public Long getMentor(){
        return this.user.getId();
    }
}
