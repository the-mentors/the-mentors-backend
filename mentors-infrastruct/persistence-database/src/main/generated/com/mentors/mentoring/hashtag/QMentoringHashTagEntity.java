package com.mentors.mentoring.hashtag;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMentoringHashTagEntity is a Querydsl query type for MentoringHashTagEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMentoringHashTagEntity extends EntityPathBase<MentoringHashTagEntity> {

    private static final long serialVersionUID = 58115494L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMentoringHashTagEntity mentoringHashTagEntity = new QMentoringHashTagEntity("mentoringHashTagEntity");

    public final com.mentors.global.common.QBaseEntity _super = new com.mentors.global.common.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final QHashTagEntity hashTag;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.mentors.mentoring.mentoring.QMentoringEntity mentoring;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QMentoringHashTagEntity(String variable) {
        this(MentoringHashTagEntity.class, forVariable(variable), INITS);
    }

    public QMentoringHashTagEntity(Path<? extends MentoringHashTagEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMentoringHashTagEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMentoringHashTagEntity(PathMetadata metadata, PathInits inits) {
        this(MentoringHashTagEntity.class, metadata, inits);
    }

    public QMentoringHashTagEntity(Class<? extends MentoringHashTagEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.hashTag = inits.isInitialized("hashTag") ? new QHashTagEntity(forProperty("hashTag"), inits.get("hashTag")) : null;
        this.mentoring = inits.isInitialized("mentoring") ? new com.mentors.mentoring.mentoring.QMentoringEntity(forProperty("mentoring"), inits.get("mentoring")) : null;
    }

}

