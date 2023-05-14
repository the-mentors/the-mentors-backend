package com.mentors.mentoring.mentoring.service;

import com.mentors.common.PageResponse;
import com.mentors.mentoring.mentoring.dto.MentoringListResponse;
import com.mentors.mentoring.mentoring.dto.MentoringSingleResponse;
import com.mentors.mentoring.hashtag.HashTagRepository;
import com.mentors.mentoring.mentoring.MentoringEntity;
import com.mentors.mentoring.mentoring.MentoringRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MentoringReadServiceImpl implements MentoringReadService{

    private final MentoringRepository mentoringRepository;
    private final HashTagRepository hashTagRepository;

    @Override
    public PageResponse<MentoringListResponse> findAll(final Pageable pageable) {
        var response = mentoringRepository.findAll(pageable);
        return new PageResponse<>(
                convertToMentoringListResponses(response.getContent()),
                response.getTotalPages(),
                response.getTotalElements(),
                response.getSize(),
                response.isLast()
        );
    }

    private List<MentoringListResponse> convertToMentoringListResponses(List<MentoringEntity> mentoringEntities) {
        return mentoringEntities.stream()
                .map(MentoringListResponse::toDto)
                .toList();
    }


    @Override
    public MentoringSingleResponse findById(final Long requesterId, final Long mentoringId) {
        var response = mentoringRepository.findByIdWithUserAndHashTags(mentoringId);
        var hashTags = hashTagRepository.findAllById(response.getHashTagIds());
        return MentoringSingleResponse.toDto(response, response.isOwner(requesterId), hashTags);
    }
}
