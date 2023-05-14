package com.mentors.category;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

import com.mentors.global.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "categories")
@NoArgsConstructor(access = PROTECTED)
public class CategoryEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "category_id")
    private Long id;

    @Column(name = "category_code", unique = true)
    private Long categoryCode;

    @Column(name = "category_name", unique = true, length = 50)
    private String categoryName;

    private Long parentCode;

    @Builder
    private CategoryEntity(final Long id,
                           final Long categoryCode,
                           final String categoryName,
                           final Long parentCode) {
        this.id = id;
        this.categoryCode = categoryCode;
        this.categoryName = categoryName;
        this.parentCode = parentCode;
    }

    public static CategoryEntity of(final Long categoryCode,
                                    final String categoryName,
                                    final Long parentCode) {
        return CategoryEntity.builder()
                .categoryCode(categoryCode)
                .categoryName(categoryName)
                .parentCode(parentCode)
                .build();
    }
}
