package com.mentors.mentoring.usecase;

import com.mentors.category.CategoryEntity;
import com.mentors.category.CategoryRepository;
import com.mentors.category.service.CategoryReadService;
import com.mentors.mentoring.dto.AddMentoringLinkRequest;
import com.mentors.mentoring.dto.AddMentoringRequest;
import com.mentors.mentoring.hashtag.service.HashTagWriteServiceImpl;
import com.mentors.mentoring.mentoring.service.MentoringWriteService;
import com.mentors.user.user.service.UserReadService;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
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

    public Long execute(final Long userId, final AddMentoringRequest request){
        final var findUser = userReadService.findUserById(userId);

        final var categories = getCategoryIdsByCodes(request.categoryCodes());
        final var hashTags = hashTagWriteService.saveAllIfDontExist(request.hashTags());

        return mentoringWriteService.addMentoring(findUser.id(), request, categories, hashTags);
    }

    private List<CategoryEntity> getCategoryIdsByCodes(final List<Long> categoryCodes) {
        return categoryCodes.stream()
                .map(code -> categoryRepository.findByCategoryCode(code).orElseThrow())
                .collect(Collectors.toList());
    }
}
