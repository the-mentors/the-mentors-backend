package com.mentors.mentoring.review;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QReviewContent is a Querydsl query type for ReviewContent
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QReviewContent extends BeanPath<ReviewContent> {

    private static final long serialVersionUID = -1795302719L;

    public static final QReviewContent reviewContent = new QReviewContent("reviewContent");

    public final StringPath content = createString("content");

    public final EnumPath<Rating> rating = createEnum("rating", Rating.class);

    public QReviewContent(String variable) {
        super(ReviewContent.class, forVariable(variable));
    }

    public QReviewContent(Path<? extends ReviewContent> path) {
        super(path.getType(), path.getMetadata());
    }

    public QReviewContent(PathMetadata metadata) {
        super(ReviewContent.class, metadata);
    }

}

