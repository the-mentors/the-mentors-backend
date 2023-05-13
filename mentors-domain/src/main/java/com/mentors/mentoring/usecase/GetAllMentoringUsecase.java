package com.mentors.mentoring.usecase;

import com.mentors.common.PageResponse;
import com.mentors.mentoring.dto.MentoringListResponse;
import com.mentors.mentoring.mentoring.service.MentoringReadService;
import com.mentors.utils.PageUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetAllMentoringUsecase {

    private final MentoringReadService mentoringReadService;

    public PageResponse<MentoringListResponse> execute(int page, int size) {
        var request = PageUtils.defaultPage(PageRequest.of(page, size));
        return mentoringReadService.findAll(request);
    }
}
