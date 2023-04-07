package com.mentors.mentoring.hashtag;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

import com.mentors.global.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = PROTECTED)
@Table(name = "hashtags")
public class HashTagEntity extends BaseEntity {

    @Id @GeneratedValue(strategy = IDENTITY)
    @Column(name = "hashtag_id")
    private Long id;

    @Embedded
    private HashTagName name;

    public HashTagEntity(final HashTagName name) {
        this.name = name;
    }

    public String getName(){
        return name.getValue();
    }
}
