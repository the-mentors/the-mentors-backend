package com.mentors.mentoring.category;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.mentors.support.RepositoryTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class MentoringCategoryRepositoryTest extends RepositoryTest {

    @Autowired
    private MentoringCategoryRepository mentoringCategoryRepository;

    @DisplayName("[Repository] 멘토링 엔티티를 통한 삭제")
    @Test
    void giveMentoringEntity_whenDeleteByMentoringEntity_thenDeletedMentoringCategoryEntities() {
        //given
        final var savedUser = initializeSavedUser();
        final var savedMentoring = initializeSavedMentoring(savedUser.getId());
        final var savedCategory = initializeSavedCategory();

        final var savedMentoringCategory =
                mentoringCategoryRepository.save(new MentoringCategoryEntity(savedMentoring, savedCategory));
        //when
        mentoringCategoryRepository.deleteAllByMentoring(savedMentoring);

        //then
        assertFalse(mentoringCategoryRepository.existsById(savedMentoringCategory.getId()));
    }
}