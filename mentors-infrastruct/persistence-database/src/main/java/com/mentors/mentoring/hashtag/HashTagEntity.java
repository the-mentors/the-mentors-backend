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

    private HashTagEntity(final Long id, final String name) {
        this.id = id;
        this.name = new HashTagName(name);
    }

    private HashTagEntity(final Long id){
        this.id = id;
    }

    public HashTagEntity(final String name){
        this(null, name);
    }

    public static HashTagEntity of(final Long id, final String name){
        return new HashTagEntity(id, name);
    }

    public static HashTagEntity of(final Long id){
        return new HashTagEntity(id);
    }

    public String getName(){
        return name.getValue();
    }
}
