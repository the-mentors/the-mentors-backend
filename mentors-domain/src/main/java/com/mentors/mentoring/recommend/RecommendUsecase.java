package com.mentors.mentoring.recommend;

import com.mentors.mentoring.mentoring.MentoringRepository;
import com.mentors.mentoring.mentoring.dto.MentoringListResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class RecommendUsecase {

    private final MentoringRepository mentoringRepository;

    public List<MentoringListResponse> execute(List<Long> mentoringId) {
        return mentoringRepository.findByIdIn(mentoringId).stream()
                .map(MentoringListResponse::toDto)
                .toList();
    }
}
