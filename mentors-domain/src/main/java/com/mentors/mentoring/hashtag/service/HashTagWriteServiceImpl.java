package com.mentors.mentoring.hashtag.service;

import com.mentors.mentoring.hashtag.HashTagEntity;
import com.mentors.mentoring.hashtag.HashTagRepository;
import com.mentors.mentoring.hashtag.MentoringHashTagEntity;
import com.mentors.mentoring.hashtag.MentoringHashTagRepository;
import com.mentors.mentoring.hashtag.mapper.HashTagDomainMapper;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class HashTagWriteServiceImpl implements HashTagWriteService {

    private final HashTagRepository hashTagRepository;
    private final MentoringHashTagRepository mentoringHashTagRepository;

    private Long saveIfDontExist(final HashTagEntity entity){
        return hashTagRepository.findByNameValue(entity.getName())
                .orElseGet(() -> hashTagRepository.save(entity))
                .getId();
    }

    @Override
    public Set<Long> saveAllIfDontExist(final List<String> name){
        return name.stream()
                .map(HashTagDomainMapper::toEntity)
                .map(this::saveIfDontExist)
                .collect(Collectors.toUnmodifiableSet());
    }

    @Override
    public void deleteNotUsedHashTags(final List<HashTagEntity> hasTags) {
        for (final HashTagEntity hasTag : hasTags) {
            if (!mentoringHashTagRepository.existsByHashTagId(hasTag.getId())) {
                hashTagRepository.delete(hasTag);
            }
        }
    }
}
