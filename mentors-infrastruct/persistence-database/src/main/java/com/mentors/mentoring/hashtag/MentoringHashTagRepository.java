package com.mentors.mentoring.hashtag;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MentoringHashTagRepository extends JpaRepository<MentoringHashTagEntity, Long> {

    List<MentoringHashTagEntity> findAllByMentoringId(Long mentoringId);
    boolean existsByHashTagId(Long hashTagId);
}
