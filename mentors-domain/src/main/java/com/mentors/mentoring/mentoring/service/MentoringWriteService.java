package com.mentors.mentoring.mentoring.service;


import com.mentors.mentoring.usecase.AddMentoringUsecase.AddMentoringCommand;

public interface MentoringWriteService {

    Long addMentoring(final Long userId, final AddMentoringCommand command);

    void deleteById(final Long userId, final Long mentoringId);
}
