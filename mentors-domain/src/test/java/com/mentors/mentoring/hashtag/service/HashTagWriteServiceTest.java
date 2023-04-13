package com.mentors.mentoring.hashtag.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.mentors.mentoring.hashtag.HashTagEntity;
import com.mentors.mentoring.hashtag.HashTagRepository;
import com.mentors.support.ServiceTest;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class HashTagWriteServiceTest extends ServiceTest {

    @Autowired
    private HashTagWriteService hashTagWriteService;

    @Autowired
    private HashTagRepository hashTagRepository;


    @DisplayName("[Service] 해시태그명을 통해 저장요청시, 중복된 해시태그명이 있으면 조회하고, 중복된 이름이 없으면 저장한다")
    @Test
    void givenHashTagNames_whenIfNotExistSaveAll_thenReturnNotDuplicateHashTagIds() {
        //given
        final var names =  List.of("duplicate", "duplicate", "notDuplicate");

        //when
        final var actual = hashTagWriteService.saveAllIfDontExist(names).stream().toList();

        //then
        assertAll(() -> {
            assertThat(actual.size()).isEqualTo(2);
            assertTrue(isExistStatusByHashTags(actual, true));
        });
    }


    @DisplayName("[Service] 해시태그 엔티티를 참조하고 있지 않을 경우 해당 해시태그 삭제")
    @Test
    void givenHashTags_whenRemoveNotReferenceHashTags_thenReturnVoid() {
        //given
        final var notUsedHashTags = initializeSavedHashTags(List.of("hashTag1", "hashTag2"));

        //when
        hashTagWriteService.deleteNotUsedHashTags(notUsedHashTags);

        //then
        assertTrue(isExistStatusByHashTags(notUsedHashTags, false));
    }

    private boolean isExistStatusByHashTags(List<HashTagEntity> hashTagEntities, boolean exist){
        for (HashTagEntity entity : hashTagEntities) {
            if (hashTagRepository.existsById(entity.getId()) != exist)
                return false;
        }
        return true;
    }
}