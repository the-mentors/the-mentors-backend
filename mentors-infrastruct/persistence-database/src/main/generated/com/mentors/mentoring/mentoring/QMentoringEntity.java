package com.mentors.mentoring.mentoring;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMentoringEntity is a Querydsl query type for MentoringEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMentoringEntity extends EntityPathBase<MentoringEntity> {

    private static final long serialVersionUID = -640427775L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMentoringEntity mentoringEntity = new QMentoringEntity("mentoringEntity");

    public final com.mentors.global.common.QBaseEntity _super = new com.mentors.global.common.QBaseEntity(this);

    public final ListPath<com.mentors.mentoring.category.MentoringCategoryEntity, com.mentors.mentoring.category.QMentoringCategoryEntity> categories = this.<com.mentors.mentoring.category.MentoringCategoryEntity, com.mentors.mentoring.category.QMentoringCategoryEntity>createList("categories", com.mentors.mentoring.category.MentoringCategoryEntity.class, com.mentors.mentoring.category.QMentoringCategoryEntity.class, PathInits.DIRECT2);

    public final QContent content;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final SetPath<com.mentors.mentoring.hashtag.MentoringHashTagEntity, com.mentors.mentoring.hashtag.QMentoringHashTagEntity> hashTags = this.<com.mentors.mentoring.hashtag.MentoringHashTagEntity, com.mentors.mentoring.hashtag.QMentoringHashTagEntity>createSet("hashTags", com.mentors.mentoring.hashtag.MentoringHashTagEntity.class, com.mentors.mentoring.hashtag.QMentoringHashTagEntity.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<MentoringLinkEntity, QMentoringLinkEntity> links = this.<MentoringLinkEntity, QMentoringLinkEntity>createList("links", MentoringLinkEntity.class, QMentoringLinkEntity.class, PathInits.DIRECT2);

    public final QPrice price;

    public final NumberPath<Double> rating = createNumber("rating", Double.class);

    public final StringPath thumbnail = createString("thumbnail");

    public final QTitle title;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final com.mentors.user.user.QUserEntity user;

    public QMentoringEntity(String variable) {
        this(MentoringEntity.class, forVariable(variable), INITS);
    }

    public QMentoringEntity(Path<? extends MentoringEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMentoringEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMentoringEntity(PathMetadata metadata, PathInits inits) {
        this(MentoringEntity.class, metadata, inits);
    }

    public QMentoringEntity(Class<? extends MentoringEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.content = inits.isInitialized("content") ? new QContent(forProperty("content")) : null;
        this.price = inits.isInitialized("price") ? new QPrice(forProperty("price")) : null;
        this.title = inits.isInitialized("title") ? new QTitle(forProperty("title")) : null;
        this.user = inits.isInitialized("user") ? new com.mentors.user.user.QUserEntity(forProperty("user")) : null;
    }

}

