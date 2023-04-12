package com.mentors.mentoring.mentoring.service;

import static com.mentors.mentoring.mentoring.mapper.MentoringDomainMapper.toEntity;
import static com.mentors.mentoring.mentoring.mapper.MentoringDomainMapper.toLinkEntities;
import static com.mentors.mentoring.mentoring.mapper.MentoringDomainMapper.toMentoringCategoryEntities;

import com.mentors.mentoring.category.MentoringCategoryRepository;
import com.mentors.mentoring.mentoring.MentoringEntity;
import com.mentors.mentoring.mentoring.MentoringLinkRepository;
import com.mentors.mentoring.mentoring.MentoringRepository;
import com.mentors.mentoring.usecase.AddMentoringUsecase.AddMentoringCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MentoringWriteServiceImpl implements MentoringWriteService{

    private final MentoringRepository mentoringRepository;
    private final MentoringLinkRepository mentoringLinkRepository;
    private final MentoringCategoryRepository mentoringCategoryRepository;

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


    @Override
    public void deleteById(final Long userId, final Long mentoringId){
        final var findMentoring = findMentoring(mentoringId);
        validateOwner(findMentoring, userId);

        mentoringLinkRepository.deleteByMentoring(findMentoring);
        mentoringCategoryRepository.deleteByMentoring(findMentoring);
        mentoringRepository.delete(findMentoring);
    }

    private void validateOwner(final MentoringEntity findMentoring,
                               final Long ownerId){
        if (!findMentoring.isOwner(ownerId))
            throw new IllegalArgumentException();
    }

    private MentoringEntity findMentoring(final Long mentoringId){
        return mentoringRepository.findById(mentoringId)
                .orElseThrow();
    }
}
