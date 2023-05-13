package com.mentors.mentoring.usecase;

import com.mentors.mentoring.mentoring.dto.MentoringSingleResponse;
import com.mentors.mentoring.mentoring.service.MentoringReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetOneMentoringUsecase {

    private final MentoringReadService mentoringReadService;

    public MentoringSingleResponse execute(final Long requesterId, final Long mentoringId) {
        return mentoringReadService.findById(requesterId, mentoringId);
    }
}
