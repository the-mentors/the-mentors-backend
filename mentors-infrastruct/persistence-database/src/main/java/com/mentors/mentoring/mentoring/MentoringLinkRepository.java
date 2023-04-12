package com.mentors.mentoring.mentoring;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MentoringLinkRepository extends JpaRepository<MentoringLinkEntity, Long> {

    void deleteByMentoring(final MentoringEntity mentoring);
}
