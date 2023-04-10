package com.mentors.api.mentoring.mentoring.usecase;

import com.mentors.api.mentoring.mentoring.dto.AddMentoringRequest;
import com.mentors.category.service.CategoryReadService;
import com.mentors.mentoring.dto.AddMentoringCommand;
import com.mentors.mentoring.hashtag.service.HashTagWriteServiceImpl;
import com.mentors.mentoring.mentoring.service.MentoringWriteService;
import com.mentors.user.user.domain.User;
import com.mentors.user.user.service.UserReadService;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddMentoringUsecase {

    private final UserReadService userReadService;
    private final CategoryReadService categoryReadService;
    private final HashTagWriteServiceImpl hashTagWriteService;
    private final MentoringWriteService mentoringWriteService;

    public Long execute(final Long userId, final AddMentoringRequest request){
        User findUser = userReadService.findUserById(userId);

        List<Long> categoryIds = getCategoryIdsByCodes(request.categoryCodes());
        Set<Long> hashTagIds = hashTagWriteService.saveAllIfDontExist(request.hashTags());

        AddMentoringCommand command = createCommand(request, categoryIds, hashTagIds);
        return mentoringWriteService.addMentoring(findUser.id(), command);
    }

    private List<Long> getCategoryIdsByCodes(final List<Long> categoryCodes){
        return categoryCodes.stream()
                .map(categoryReadService::findCategoryIdByCode)
                .collect(Collectors.toList());
    }

    private AddMentoringCommand createCommand(final AddMentoringRequest request,
                                              final List<Long> categoryIds,
                                              final Set<Long> hashTagIds){
        return new AddMentoringCommand(
                request.title(),
                request.content(),
                request.thumbnail(),
                request.price(),
                hashTagIds,
                categoryIds,
                request.links()
        );
    }

}
