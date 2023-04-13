package com.mentors.support;

import static com.mentors.MentoringEntityFixture.*;
import static com.mentors.UserEntityFixture.*;

import com.mentors.MentoringEntityFixture;
import com.mentors.UserEntityFixture;
import com.mentors.UserFixture;
import com.mentors.lib.DatabaseCleaner;
import com.mentors.mentoring.hashtag.HashTagEntity;
import com.mentors.mentoring.hashtag.HashTagRepository;
import com.mentors.mentoring.hashtag.mapper.HashTagDomainMapper;
import com.mentors.mentoring.mentoring.MentoringEntity;
import com.mentors.mentoring.mentoring.MentoringRepository;
import com.mentors.user.user.UserEntity;
import com.mentors.user.user.UserRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@Profile("test")
@Import(TestPasswordEncoder.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class ServiceTest{
    @Autowired
    private DatabaseCleaner databaseCleaner;

    @Autowired
    protected UserRepository userRepository;

    @Autowired
    protected MentoringRepository mentoringRepository;

    @Autowired
    protected HashTagRepository hashTagRepository;

    @BeforeEach
    void setUp() {
        databaseCleaner.execute();
    }

    protected UserEntity initializeSavedUser(){
        return userRepository.save(기본유저_엔티티());
    }

    protected MentoringEntity initializeSavedMentoring(final Long userId){
        return mentoringRepository.save(멘토링_등록(userId));
    }

    protected List<HashTagEntity> initializeSavedHashTags(final List<String> names) {
        final var hashTags = names.stream().map(HashTagDomainMapper::toEntity).toList();
        return hashTagRepository.saveAll(hashTags);
    }
}
