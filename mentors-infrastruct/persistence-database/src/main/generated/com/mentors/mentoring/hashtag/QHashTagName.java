package com.mentors.mentoring.hashtag;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QHashTagName is a Querydsl query type for HashTagName
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QHashTagName extends BeanPath<HashTagName> {

    private static final long serialVersionUID = 1811840351L;

    public static final QHashTagName hashTagName = new QHashTagName("hashTagName");

    public final StringPath value = createString("value");

    public QHashTagName(String variable) {
        super(HashTagName.class, forVariable(variable));
    }

    public QHashTagName(Path<? extends HashTagName> path) {
        super(path.getType(), path.getMetadata());
    }

    public QHashTagName(PathMetadata metadata) {
        super(HashTagName.class, metadata);
    }

}

