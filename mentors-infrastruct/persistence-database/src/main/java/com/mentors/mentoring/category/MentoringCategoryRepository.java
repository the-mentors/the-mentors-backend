package com.mentors.mentoring.category;

import com.mentors.mentoring.mentoring.MentoringEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MentoringCategoryRepository extends JpaRepository<MentoringCategoryEntity, Long> {

    void deleteByMentoring(final MentoringEntity mentoring);
}
