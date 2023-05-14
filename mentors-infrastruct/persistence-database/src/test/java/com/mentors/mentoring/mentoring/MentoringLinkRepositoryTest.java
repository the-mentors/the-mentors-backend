package com.mentors.mentoring.mentoring;

import static org.junit.jupiter.api.Assertions.assertFalse;

import com.mentors.mentoring.LinkType;
import com.mentors.support.RepositoryTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class MentoringLinkRepositoryTest extends RepositoryTest {

    @Autowired
    private MentoringLinkRepository mentoringLinkRepository;


    @DisplayName("[Repository] 멘토링 엔티티를 통한 삭제")
    @Test
    void giveMentoringEntity_whenDeleteByMentoringEntity_thenDeletedMentoringLinkEntities() {
        //given
        final var savedUser = initializeSavedUser();
        final var savedMentoring = initializeSavedMentoring(savedUser.getId());

        final var savedMentoringCategory =
                mentoringLinkRepository.save(new MentoringLinkEntity(savedMentoring, LinkType.OTHER, "dummy"));

        //when
        mentoringLinkRepository.deleteAllByMentoring(savedMentoring);

        //then
        assertFalse(mentoringLinkRepository.existsById(savedMentoringCategory.getId()));
    }
}