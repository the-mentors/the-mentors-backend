package com.mentors.mentoring.hashtag;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.mentors.mentoring.mentoring.MentoringEntity;
import com.mentors.support.RepositoryTest;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class MentoringHashTagRepositoryTest extends RepositoryTest {

    @Autowired
    private MentoringHashTagRepository mentoringHashTagRepository;


    @DisplayName("[Repository] 해시태그명을 통한 조회")
    @Test
    void giveMentoringId_whenFindByMentoringId_thenReturnMentoringHashTagEntities() {
        //given
        final int size = 10;
        final var savedMentoring = initializeSavedMentoring();
        final var savedHashTags = initializeSavedHashtags(size);
        initializeSavedMentoringHashtags(savedHashTags, savedMentoring);

        //when
        final var actual = mentoringHashTagRepository.findAllByMentoringId(savedMentoring.getId());

        //then
        assertAll(() -> {
            assertThat(actual.size()).isEqualTo(size);
            assertThat(isSameMentoring(actual, savedMentoring)).isTrue();
            assertThat(isSameHashTags(actual, savedHashTags)).isTrue();
       });
    }

    @DisplayName("[Repository] 해시태그 아이디가 존재할 경우 Ture 반환")
    @Test
    void giveExistHashTagId_whenFindExistMentoringHashTag_thenReturnTure() {
        //given
        final var savedMentoring = initializeSavedMentoring();
        final var savedHashTag = initializeSavedHashTag("dummyHashTag");
        mentoringHashTagRepository.save(new MentoringHashTagEntity(savedMentoring, savedHashTag));

        //when & then
        assertTrue(mentoringHashTagRepository.existsByHashTagId(savedHashTag.getId()));
    }

    @DisplayName("[Repository] 해시태그 아이디가 존재하지않을 경우 False 반환")
    @Test
    void giveNotExistHashTagId_whenFindExistMentoringHashTag_thenReturnFalse() {
        //given
        final Long notExistHashTagId = 0L;

        //when & then
        assertFalse(mentoringHashTagRepository.existsByHashTagId(notExistHashTagId));
    }

    private boolean isSameMentoring(final List<MentoringHashTagEntity> mentoringHashTagEntities,
                                    final MentoringEntity savedMentoringEntity){
        for (MentoringHashTagEntity mentoringHashTagEntity : mentoringHashTagEntities) {
            if (!mentoringHashTagEntity.getMentoring().equals(savedMentoringEntity))
                return false;
        }
        return true;
    }

    private boolean isSameHashTags(final List<MentoringHashTagEntity> mentoringHashTagEntities,
                                   final List<HashTagEntity> hashTagEntities) {
        for (HashTagEntity hashTag : hashTagEntities) {
            boolean isSame = mentoringHashTagEntities.stream()
                    .map(entity -> hashTag.getId().equals(entity.getHashTag().getId()))
                    .findAny()
                    .isPresent();

            if (!isSame) return false;
        }
        return true;
    }

    private MentoringEntity initializeSavedMentoring(){
        final var savedUser = initializeSavedUser();
        return initializeSavedMentoring(savedUser.getId());
    }

    private List<HashTagEntity> initializeSavedHashtags(final int size) {
        return IntStream.range(0, size)
                .mapToObj(i -> initializeSavedHashTag("hashTag" + i))
                .collect(Collectors.toList());
    }

    private List<MentoringHashTagEntity> initializeSavedMentoringHashtags(
            final List<HashTagEntity> savedHashTags,
            final MentoringEntity savedMentoring){
        return savedHashTags.stream()
                .map(savedHashTag -> new MentoringHashTagEntity(savedMentoring, savedHashTag))
                .map(entity -> mentoringHashTagRepository.save(entity))
                .collect(Collectors.toList());
    }
}