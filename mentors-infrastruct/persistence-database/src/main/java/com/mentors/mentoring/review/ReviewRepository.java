package com.mentors.mentoring.review;

import com.mentors.user.user.UserEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReviewRepository extends JpaRepository<ReviewEntity, Long> {
    @Query(value = "SELECT r FROM ReviewEntity r JOIN FETCH r.reviewer WHERE r.mentoringId = :mentoringId ")
    Slice<ReviewEntity> findAllByMentoringIdOrderByIdDesc(Long mentoringId, Pageable pageable);
    boolean existsByReviewerAndMentoringId(UserEntity reviewer, Long mentoringId);
}
