package com.mentors.mentoring.category;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

import com.mentors.category.CategoryEntity;
import com.mentors.global.common.BaseEntity;
import com.mentors.mentoring.mentoring.MentoringEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = PROTECTED)
@Table(name = "mentoring_categories")
public class MentoringCategoryEntity extends BaseEntity {

    @Id @GeneratedValue(strategy = IDENTITY)
    @Column(name = "mentoring_category_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "mentoring_id", nullable = false)
    private MentoringEntity mentoring;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private CategoryEntity category;

    @Builder
    public MentoringCategoryEntity(final MentoringEntity mentoring,
                                   final CategoryEntity category) {
        this.mentoring = mentoring;
        this.category = category;
    }

    public static MentoringCategoryEntity of(final Long categoryId){
        return new MentoringCategoryEntity(null, CategoryEntity.of(categoryId));
    }

    public void addMentoring(final MentoringEntity mentoring){
        this.mentoring = mentoring;
    }
}
