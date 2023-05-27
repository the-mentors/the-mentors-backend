package com.mentors.mentoring.mentoring;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MentoringRepository extends JpaRepository<MentoringEntity, Long> {
    @Query(value = "select m from MentoringEntity m join fetch m.user",
            countQuery = "select count(m) from MentoringEntity m")
    Page<MentoringEntity> findAll(Pageable pageable);

    @Query("select m from MentoringEntity m join fetch m.user join fetch m.hashTags where m.id = :id")
    MentoringEntity findByIdWithUserAndHashTags(Long id);
}
