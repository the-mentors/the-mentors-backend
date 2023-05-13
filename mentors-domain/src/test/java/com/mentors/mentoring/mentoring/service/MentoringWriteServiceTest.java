package com.mentors.mentoring.mentoring.service;

import static java.util.Collections.emptyList;
import static java.util.Collections.emptySet;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import com.mentors.mentoring.dto.AddMentoringRequest;
import com.mentors.support.ServiceTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class MentoringWriteServiceTest extends ServiceTest {

    @Autowired
    private MentoringWriteService mentoringWriteService;

    @DisplayName("[Service] 요청정보에 따른 멘도링 등록 성공")
    @Test
    void givenAddMentoringRequestAndNoneOtherData_whenSavingMentoring_thenReturnMentoringId() {
        //given
        final var savedUser = initializeSavedUser();
        final var request = addMentoringRequest();

        //when
        final Long actual = mentoringWriteService.addMentoring(savedUser, request, emptyList(), emptySet());

        //then
        assertThat(mentoringRepository.existsById(actual)).isTrue();
    }

    @DisplayName("[Service] 멘토링 삭제 요청 성공")
    @Test
    void givenAddMentoringId_whenDeleteMentoringById_thenReturnVoid() {
        //given
        final var savedUser = initializeSavedUser();
        final var savedMentoring = initializeSavedMentoring(savedUser.getId());

        //when
        mentoringWriteService.deleteById(savedUser.getId(), savedMentoring.getId());

        //then
        assertThat(mentoringRepository.existsById(savedMentoring.getId())).isFalse();
    }

    @DisplayName("[Service] 멘토링 삭제 요청시, 작성자가 아닌경우 에러발생")
    @Test
    void givenAddMentoringIdAndNotMatchOwnerId_whenDeleteMentoringById_thenThrowsException() {
        //given
        final var savedMentoring = initializeSavedMentoring(initializeSavedUser().getId());
        final var notMatchOwnerId = 0L;

        //when
        Assertions.assertAll(() -> {
            assertThatCode(() -> mentoringWriteService.deleteById(notMatchOwnerId, savedMentoring.getId()))
                    .isInstanceOf(IllegalArgumentException.class);
            assertThat(mentoringRepository.existsById(savedMentoring.getId())).isTrue();

        });
    }

    private AddMentoringRequest addMentoringRequest() {
        return new AddMentoringRequest("content",
                "content",
                "thumbnail",
                1000,
                null,
                null,
                emptyList());
    }
}