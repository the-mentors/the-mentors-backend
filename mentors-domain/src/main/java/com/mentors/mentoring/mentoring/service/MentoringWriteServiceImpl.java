package com.mentors.mentoring.mentoring.service;

import static com.mentors.mentoring.mentoring.mapper.MentoringDomainMapper.toEntity;
import static com.mentors.mentoring.mentoring.mapper.MentoringDomainMapper.toLinkEntities;

import com.mentors.category.CategoryEntity;
import com.mentors.mentoring.category.MentoringCategoryRepository;
import com.mentors.mentoring.mentoring.dto.AddMentoringLinkRequest;
import com.mentors.mentoring.mentoring.dto.AddMentoringRequest;
import com.mentors.mentoring.hashtag.HashTagEntity;
import com.mentors.mentoring.mentoring.MentoringEntity;
import com.mentors.mentoring.mentoring.MentoringLinkRepository;
import com.mentors.mentoring.mentoring.MentoringRepository;
import com.mentors.user.user.UserEntity;
import java.util.List;
import java.util.Set;
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
    public Long addMentoring(final UserEntity user,
                             final AddMentoringRequest request,
                             final List<CategoryEntity> categories,
                             final Set<HashTagEntity> hashTags) {
        var mentoringEntity = toEntity(user, request);
        initializeAdditionalInformation(mentoringEntity, categories, hashTags, request.links());
        return mentoringRepository.save(mentoringEntity).getId();
    }

    private void initializeAdditionalInformation(final MentoringEntity entity,
                                                 final List<CategoryEntity> categories,
                                                 final Set<HashTagEntity> hashTags,
                                                 final List<AddMentoringLinkRequest> links) {
        entity.addMentoringCategories(categories);
        entity.addMentoringHashTags(hashTags);
        entity.addMentoringLinks(toLinkEntities(links));
    }

    @Override
    public void deleteById(final Long userId, final Long mentoringId){
        final var findMentoring = findMentoring(mentoringId);
        validateOwner(findMentoring, userId);

        mentoringLinkRepository.deleteAllByMentoring(findMentoring);
        mentoringCategoryRepository.deleteAllByMentoring(findMentoring);
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
