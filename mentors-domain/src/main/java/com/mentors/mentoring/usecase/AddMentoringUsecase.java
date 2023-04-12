package com.mentors.mentoring.usecase;

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
    private final CategoryReadService categoryReadService;
    private final HashTagWriteServiceImpl hashTagWriteService;
    private final MentoringWriteService mentoringWriteService;

    public Long execute(final Long userId, final AddMentoringRequest request){
        final var findUser = userReadService.findUserById(userId);

        final var categoryIds = getCategoryIdsByCodes(request.categoryCodes());
        final var hashTagIds = hashTagWriteService.saveAllIfDontExist(request.hashTags());
        var command = createCommand(request, categoryIds, hashTagIds);

        return mentoringWriteService.addMentoring(findUser.id(), command);
    }


    public record AddMentoringCommand(String title,
                                      String content,
                                      String thumbnail,
                                      Integer price,
                                      Set<Long> hashTags,
                                      List<Long> categories,
                                      List<AddMentoringLinkRequest> links) {
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
