package com.mentors.mentoring.hashtag;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QHashTagEntity is a Querydsl query type for HashTagEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QHashTagEntity extends EntityPathBase<HashTagEntity> {

    private static final long serialVersionUID = 1471381943L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QHashTagEntity hashTagEntity = new QHashTagEntity("hashTagEntity");

    public final com.mentors.global.common.QBaseEntity _super = new com.mentors.global.common.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QHashTagName name;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QHashTagEntity(String variable) {
        this(HashTagEntity.class, forVariable(variable), INITS);
    }

    public QHashTagEntity(Path<? extends HashTagEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QHashTagEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QHashTagEntity(PathMetadata metadata, PathInits inits) {
        this(HashTagEntity.class, metadata, inits);
    }

    public QHashTagEntity(Class<? extends HashTagEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.name = inits.isInitialized("name") ? new QHashTagName(forProperty("name")) : null;
    }

}

