package com.mentors.mypage.usecase;

import com.mentors.mentoring.mentoring.MentoringLinkEntity;
import com.mentors.mentoring.mentoring.MentoringLinkRepository;
import com.mentors.mentoring.mentoring.MentoringRepository;
import com.mentors.mypage.service.MyPageWriteService;
import com.mentors.user.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional()
@RequiredArgsConstructor
public class AddMyPageUsecase {
    private final MyPageWriteService myPageWriteService;
    private final UserRepository userRepository;
    private final MentoringRepository mentoringRepository;

    public void execute(Long menteeId, Long mentoringId) {
        var findMentoring = mentoringRepository.findById(mentoringId).orElseThrow();
        var mentor = userRepository.findById(findMentoring.getMentor()).orElseThrow();
        var mentee = userRepository.findById(menteeId).orElseThrow();
        myPageWriteService.save(findMentoring,mentor,mentee);
    }
}
