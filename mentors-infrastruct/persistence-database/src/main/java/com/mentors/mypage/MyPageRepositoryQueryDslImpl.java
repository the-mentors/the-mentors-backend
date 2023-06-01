package com.mentors.mypage;

import com.mentors.global.common.SliceUtils;
import com.mentors.user.user.QUserEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;

import java.util.List;

import static com.mentors.mentoring.mentoring.QMentoringEntity.mentoringEntity;
import static com.mentors.mentoring.mentoring.QMentoringLinkEntity.*;
import static com.mentors.mypage.QMyPageEntity.myPageEntity;

@RequiredArgsConstructor
public class MyPageRepositoryQueryDslImpl implements MyPageRepositoryQueryDsl {
    private final JPAQueryFactory queryFactory;

    private final SliceUtils<MyPageEntity> sliceUtils;


    // 멘티, 멘토, 시간, 아이디
    @Override
    public Slice<MyPageEntity> findAllByUserId(Long userId, Pageable pageable) {
        List<MyPageEntity> content = queryFactory.selectFrom(myPageEntity)
                .leftJoin(myPageEntity.mentee, new QUserEntity("mentee"))
                .leftJoin(myPageEntity.mentor, new QUserEntity("mentor"))
                .leftJoin(myPageEntity.mentoring, mentoringEntity)
                .leftJoin(myPageEntity.mentoring.links, mentoringLinkEntity)
                .where(myPageEntity.mentee.id.eq(userId))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize()+1)
                .fetch();
        boolean hasNext = sliceUtils.hasNext(pageable, content);
        return new SliceImpl<>(content, pageable, hasNext);
    }

    @Override
    public Slice<MyPageEntity> findAllByMentorId(Long mentorId, Pageable pageable) {
        List<MyPageEntity> content = queryFactory.selectFrom(myPageEntity)
                .leftJoin(myPageEntity.mentee, new QUserEntity("mentee"))
                .leftJoin(myPageEntity.mentor, new QUserEntity("mentor"))
                .leftJoin(myPageEntity.mentoring, mentoringEntity)
                .leftJoin(myPageEntity.mentoring.links, mentoringLinkEntity)
                .where(myPageEntity.mentor.id.eq(mentorId))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize()+1)
                .fetch();
        boolean hasNext = sliceUtils.hasNext(pageable, content);
        return new SliceImpl<>(content, pageable, hasNext);
    }

}


