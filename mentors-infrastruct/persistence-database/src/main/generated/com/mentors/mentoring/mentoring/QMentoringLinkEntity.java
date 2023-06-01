package com.mentors.mentoring.mentoring;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMentoringLinkEntity is a Querydsl query type for MentoringLinkEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMentoringLinkEntity extends EntityPathBase<MentoringLinkEntity> {

    private static final long serialVersionUID = 484856475L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMentoringLinkEntity mentoringLinkEntity = new QMentoringLinkEntity("mentoringLinkEntity");

    public final com.mentors.global.common.QBaseEntity _super = new com.mentors.global.common.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final EnumPath<com.mentors.mentoring.LinkType> linkType = createEnum("linkType", com.mentors.mentoring.LinkType.class);

    public final StringPath linkUrl = createString("linkUrl");

    public final QMentoringEntity mentoring;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QMentoringLinkEntity(String variable) {
        this(MentoringLinkEntity.class, forVariable(variable), INITS);
    }

    public QMentoringLinkEntity(Path<? extends MentoringLinkEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMentoringLinkEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMentoringLinkEntity(PathMetadata metadata, PathInits inits) {
        this(MentoringLinkEntity.class, metadata, inits);
    }

    public QMentoringLinkEntity(Class<? extends MentoringLinkEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.mentoring = inits.isInitialized("mentoring") ? new QMentoringEntity(forProperty("mentoring"), inits.get("mentoring")) : null;
    }

}

