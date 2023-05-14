package com.mentors.mentoring.review;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewMentoringRepository extends JpaRepository<ReviewMentoringEntity, Long> {

    Optional<ReviewMentoringEntity> findByMentoringId(Long mentoringId);
}
