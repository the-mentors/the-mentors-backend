package com.mentors.mentoring.usecase;

import com.mentors.mentoring.dto.MentoringSingleResponse;
import com.mentors.mentoring.mentoring.service.MentoringReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GetOneMentoringUsecase {

    private final MentoringReadService mentoringReadService;

    public MentoringSingleResponse execute(final Long mentoringId) {
        return mentoringReadService.findById(mentoringId);
    }
}
