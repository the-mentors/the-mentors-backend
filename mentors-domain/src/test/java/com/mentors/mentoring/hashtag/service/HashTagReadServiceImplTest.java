package com.mentors.mentoring.hashtag.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import com.mentors.mentoring.hashtag.HashTagEntity;
import com.mentors.mentoring.hashtag.MentoringHashTagEntity;
import com.mentors.mentoring.hashtag.MentoringHashTagRepository;
import com.mentors.mentoring.mentoring.MentoringEntity;
import com.mentors.support.ServiceTest;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


class HashTagReadServiceImplTest extends ServiceTest {

    @Autowired
    private HashTagReadService hashTagReadService;

    @Autowired
    private MentoringHashTagRepository mentoringHashTagRepository;

    @DisplayName("[Service] 멘토링 아이디를 통해 현재 참조되고 있는 해시태그 엔티티 조회")
    @Test
    void givenMentoringId_whenFindUsedHashTags_thenReturnHashTags() {
        //given
        final var mentoring = initializeSavedMentoring();
        final var hashTags = initializeSavedHashTags(List.of("hashTag1", "hashTag2"));
        initializeSavedMentoringHashTagEntities(mentoring, hashTags);

        //when
        final var actual = hashTagReadService.findAllByMentoringId(mentoring.getId());

        //then
        assertAll(() -> {
            assertThat(actual.size()).isEqualTo(2);
            assertThat(actual).contains(hashTags.get(0), hashTags.get(1));
        });
    }

    private MentoringEntity initializeSavedMentoring(){
        final var savedUser = initializeSavedUser();
        return initializeSavedMentoring(savedUser.getId());
    }

    private List<MentoringHashTagEntity> initializeSavedMentoringHashTagEntities(final MentoringEntity mentoring,
                                                                                 final List<HashTagEntity> hashTags) {
        return hashTags.stream()
                .map(hashTag -> mentoringHashTagRepository.save(MentoringHashTagEntity.of(mentoring, hashTag)))
                .collect(Collectors.toList());
    }

}