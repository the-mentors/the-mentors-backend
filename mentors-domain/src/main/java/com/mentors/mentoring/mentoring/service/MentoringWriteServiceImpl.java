package com.mentors.mentoring.mentoring.service;

import static com.mentors.mentoring.mentoring.mapper.MentoringDomainMapper.toEntity;
import static com.mentors.mentoring.mentoring.mapper.MentoringDomainMapper.toLinkEntities;
import static com.mentors.mentoring.mentoring.mapper.MentoringDomainMapper.toMentoringCategoryEntities;

import com.mentors.mentoring.dto.AddMentoringCommand;
import com.mentors.mentoring.mentoring.MentoringEntity;
import com.mentors.mentoring.mentoring.MentoringRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MentoringWriteServiceImpl implements MentoringWriteService{

    private final MentoringRepository mentoringRepository;

    @Override
    public Long addMentoring(final Long userId, final AddMentoringCommand command){
        MentoringEntity entity = toEntity(userId, command);
        initializeAdditionalInformation(entity, command);
        return mentoringRepository.save(entity).getId();
    }

    private void initializeAdditionalInformation(final MentoringEntity entity, final AddMentoringCommand command) {
        entity.addMentoringCategories(toMentoringCategoryEntities(command.categories()));
        entity.addMentoringHashTags(command.hashTags());
        entity.addMentoringLinks(toLinkEntities(command.links()));
    }
}
