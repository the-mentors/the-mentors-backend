package com.mentors.mentoring.category;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMentoringCategoryEntity is a Querydsl query type for MentoringCategoryEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMentoringCategoryEntity extends EntityPathBase<MentoringCategoryEntity> {

    private static final long serialVersionUID = 706122824L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMentoringCategoryEntity mentoringCategoryEntity = new QMentoringCategoryEntity("mentoringCategoryEntity");

    public final com.mentors.global.common.QBaseEntity _super = new com.mentors.global.common.QBaseEntity(this);

    public final com.mentors.category.QCategoryEntity category;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.mentors.mentoring.mentoring.QMentoringEntity mentoring;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QMentoringCategoryEntity(String variable) {
        this(MentoringCategoryEntity.class, forVariable(variable), INITS);
    }

    public QMentoringCategoryEntity(Path<? extends MentoringCategoryEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMentoringCategoryEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMentoringCategoryEntity(PathMetadata metadata, PathInits inits) {
        this(MentoringCategoryEntity.class, metadata, inits);
    }

    public QMentoringCategoryEntity(Class<? extends MentoringCategoryEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.category = inits.isInitialized("category") ? new com.mentors.category.QCategoryEntity(forProperty("category")) : null;
        this.mentoring = inits.isInitialized("mentoring") ? new com.mentors.mentoring.mentoring.QMentoringEntity(forProperty("mentoring"), inits.get("mentoring")) : null;
    }

}

