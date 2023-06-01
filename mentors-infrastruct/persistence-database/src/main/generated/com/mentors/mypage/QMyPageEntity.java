package com.mentors.mypage;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMyPageEntity is a Querydsl query type for MyPageEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMyPageEntity extends EntityPathBase<MyPageEntity> {

    private static final long serialVersionUID = 1884004616L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMyPageEntity myPageEntity = new QMyPageEntity("myPageEntity");

    public final com.mentors.global.common.QBaseEntity _super = new com.mentors.global.common.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.mentors.user.user.QUserEntity mentee;

    public final com.mentors.user.user.QUserEntity mentor;

    public final com.mentors.mentoring.mentoring.QMentoringEntity mentoring;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QMyPageEntity(String variable) {
        this(MyPageEntity.class, forVariable(variable), INITS);
    }

    public QMyPageEntity(Path<? extends MyPageEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMyPageEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMyPageEntity(PathMetadata metadata, PathInits inits) {
        this(MyPageEntity.class, metadata, inits);
    }

    public QMyPageEntity(Class<? extends MyPageEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.mentee = inits.isInitialized("mentee") ? new com.mentors.user.user.QUserEntity(forProperty("mentee")) : null;
        this.mentor = inits.isInitialized("mentor") ? new com.mentors.user.user.QUserEntity(forProperty("mentor")) : null;
        this.mentoring = inits.isInitialized("mentoring") ? new com.mentors.mentoring.mentoring.QMentoringEntity(forProperty("mentoring"), inits.get("mentoring")) : null;
    }

}

