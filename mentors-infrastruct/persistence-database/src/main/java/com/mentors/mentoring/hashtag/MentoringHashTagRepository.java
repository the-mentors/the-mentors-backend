package com.mentors.mentoring.hashtag;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MentoringHashTagRepository extends JpaRepository<MentoringHashTagEntity, Long> {

    List<MentoringHashTagEntity> findAllByMentoringId(final Long mentoringId);
    boolean existsByHashTagId(final Long hashTagId);
}
