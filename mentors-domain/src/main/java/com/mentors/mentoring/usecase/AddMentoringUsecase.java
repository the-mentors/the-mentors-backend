package com.mentors.mentoring.usecase;

import static com.mentors.user.user.mapper.UserDomainMapper.*;

import com.mentors.category.CategoryEntity;
import com.mentors.category.CategoryRepository;
import com.mentors.mentoring.mentoring.dto.AddMentoringRequest;
import com.mentors.mentoring.hashtag.service.HashTagWriteServiceImpl;
import com.mentors.mentoring.mentoring.service.MentoringWriteService;
import com.mentors.mentoring.review.event.ReviewSaveEvent;
import com.mentors.user.user.service.UserReadService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AddMentoringUsecase {

    private final UserReadService userReadService;
    private final CategoryRepository categoryRepository;
    private final HashTagWriteServiceImpl hashTagWriteService;
    private final MentoringWriteService mentoringWriteService;
    private final ApplicationEventPublisher applicationEventPublisher;

    public Long execute(final Long userId, final AddMentoringRequest request){
        final var findUser = toEntity(userReadService.findUserById(userId));
        final var categories = getCategoryIdsByCodes(request.categoryCodes());
        final var hashTags = hashTagWriteService.saveAllIfDontExist(request.hashTags());
        final Long id = mentoringWriteService.addMentoring(findUser, request, categories, hashTags);
        applicationEventPublisher.publishEvent(new ReviewSaveEvent(id));
        return id;
    }

    private List<CategoryEntity> getCategoryIdsByCodes(final List<Long> categoryCodes) {
        return categoryCodes.stream()
                .map(code -> categoryRepository.findByCategoryCode(code).orElseThrow())
                .collect(Collectors.toList());
    }
}
