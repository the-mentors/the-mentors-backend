package com.mentors.mentoring.usecase;

import com.mentors.mentoring.hashtag.service.HashTagReadService;
import com.mentors.mentoring.hashtag.service.HashTagWriteService;
import com.mentors.mentoring.mentoring.service.MentoringWriteService;
import com.mentors.user.user.domain.User;
import com.mentors.user.user.service.UserReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class DeleteMentoringUsecase {

    private final UserReadService userReadService;
    private final MentoringWriteService mentoringWriteService;
    private final HashTagReadService hashTagReadService;
    private final HashTagWriteService hashTagWriteService;

    public void execute(final Long userId, final Long mentoringId){
        userReadService.validateExistById(userId);

        final var findHashTags = hashTagReadService.findAllByMentoringId(mentoringId);
        mentoringWriteService.deleteById(userId, mentoringId);
        hashTagWriteService.deleteNotUsedHashTags(findHashTags);
    }
}
