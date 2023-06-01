package com.mentors.mentoring.review;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QReviewMentoringEntity is a Querydsl query type for ReviewMentoringEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReviewMentoringEntity extends EntityPathBase<ReviewMentoringEntity> {

    private static final long serialVersionUID = -1332889076L;

    public static final QReviewMentoringEntity reviewMentoringEntity = new QReviewMentoringEntity("reviewMentoringEntity");

    public final com.mentors.global.common.QBaseEntity _super = new com.mentors.global.common.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Integer> fiveCount = createNumber("fiveCount", Integer.class);

    public final NumberPath<Integer> fourCount = createNumber("fourCount", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> mentoringId = createNumber("mentoringId", Long.class);

    public final NumberPath<Integer> oneCount = createNumber("oneCount", Integer.class);

    public final NumberPath<Double> ratingAverage = createNumber("ratingAverage", Double.class);

    public final NumberPath<Integer> threeCount = createNumber("threeCount", Integer.class);

    public final NumberPath<Integer> totalCount = createNumber("totalCount", Integer.class);

    public final NumberPath<Double> totalRating = createNumber("totalRating", Double.class);

    public final NumberPath<Integer> twoCount = createNumber("twoCount", Integer.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QReviewMentoringEntity(String variable) {
        super(ReviewMentoringEntity.class, forVariable(variable));
    }

    public QReviewMentoringEntity(Path<? extends ReviewMentoringEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QReviewMentoringEntity(PathMetadata metadata) {
        super(ReviewMentoringEntity.class, metadata);
    }

}

