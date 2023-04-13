package com.mentors.mentoring.hashtag;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import com.mentors.support.RepositoryTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class HashTagRepositoryTest extends RepositoryTest {

    @Autowired
    private HashTagRepository hashTagRepository;

    @DisplayName("[Repository] 해시태그명을 통한 조회")
    @Test
    void giveHashTagName_whenFindByName_thenReturnHashTag() {
        //given
        final var hashTagName = "name";
        final var savedHashTag = initializeSavedHashTag(hashTagName);

        //when
        final var actual = hashTagRepository.findByNameValue(hashTagName).get();

        //then
        assertAll(() -> {
            assertThat(actual.getId()).isEqualTo(savedHashTag.getId());
            assertThat(actual.getName()).isEqualTo(hashTagName);
        });
    }

}