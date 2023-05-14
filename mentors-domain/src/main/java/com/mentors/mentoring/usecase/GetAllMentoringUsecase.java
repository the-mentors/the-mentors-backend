package com.mentors.mentoring.usecase;

import com.mentors.common.PageResponse;
import com.mentors.mentoring.mentoring.dto.MentoringListResponse;
import com.mentors.mentoring.mentoring.service.MentoringReadService;
import com.mentors.utils.PageUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetAllMentoringUsecase {

    private final MentoringReadService mentoringReadService;

    public PageResponse<MentoringListResponse> execute(Pageable pageable) {
        var request = PageUtils.defaultPage(pageable);
        return mentoringReadService.findAll(request);
    }
}
