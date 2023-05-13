package com.mentors.mentoring.mentoring.service;

import com.mentors.common.PageResponse;
import com.mentors.mentoring.dto.MentoringListResponse;
import com.mentors.mentoring.dto.MentoringSingleResponse;
import org.springframework.data.domain.Pageable;

public interface MentoringReadService {

    PageResponse<MentoringListResponse> findAll(Pageable pageable);

    MentoringSingleResponse findById(final Long requesterId, final Long mentoringId);
}
