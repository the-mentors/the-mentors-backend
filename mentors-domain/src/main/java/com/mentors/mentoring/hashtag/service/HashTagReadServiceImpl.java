package com.mentors.mentoring.hashtag.service;

import com.mentors.mentoring.hashtag.HashTagEntity;
import com.mentors.mentoring.hashtag.MentoringHashTagEntity;
import com.mentors.mentoring.hashtag.MentoringHashTagRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class HashTagReadServiceImpl implements HashTagReadService {

    private final MentoringHashTagRepository mentoringHashTagRepository;

    @Override
    public List<HashTagEntity> findAllByMentoringId(final Long mentoringId){
        return mentoringHashTagRepository.findAllByMentoringId(mentoringId).stream()
                .map(MentoringHashTagEntity::getHashTag)
                .collect(Collectors.toList());
    }
}
