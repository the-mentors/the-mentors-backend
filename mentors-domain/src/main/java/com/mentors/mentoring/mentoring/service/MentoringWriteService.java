package com.mentors.mentoring.mentoring.service;

import com.mentors.mentoring.dto.AddMentoringCommand;

public interface MentoringWriteService {

    Long addMentoring(final Long userId, final AddMentoringCommand command);
}
