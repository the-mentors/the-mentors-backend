package com.mentors.authority;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAuthority is a Querydsl query type for Authority
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAuthority extends EntityPathBase<Authority> {

    private static final long serialVersionUID = -1672273873L;

    public static final QAuthority authority = new QAuthority("authority");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final EnumPath<com.mentors.user.Role> role = createEnum("role", com.mentors.user.Role.class);

    public final SetPath<com.mentors.user.user.UserEntity, com.mentors.user.user.QUserEntity> users = this.<com.mentors.user.user.UserEntity, com.mentors.user.user.QUserEntity>createSet("users", com.mentors.user.user.UserEntity.class, com.mentors.user.user.QUserEntity.class, PathInits.DIRECT2);

    public QAuthority(String variable) {
        super(Authority.class, forVariable(variable));
    }

    public QAuthority(Path<? extends Authority> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAuthority(PathMetadata metadata) {
        super(Authority.class, metadata);
    }

}

