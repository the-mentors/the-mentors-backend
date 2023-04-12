package com.mentors.support;

import static com.mentors.CategoryEntityFixture.toEntity;
import static com.mentors.MentoringEntityFixture.멘토링_등록;
import static com.mentors.UserEntityFixture.기본유저_엔티티;

import com.mentors.category.CategoryEntity;
import com.mentors.category.CategoryRepository;
import com.mentors.global.config.JpaConfiguration;
import com.mentors.mentoring.hashtag.HashTagEntity;
import com.mentors.mentoring.hashtag.HashTagRepository;
import com.mentors.mentoring.mentoring.MentoringEntity;
import com.mentors.mentoring.mentoring.MentoringRepository;
import com.mentors.user.user.UserEntity;
import com.mentors.user.user.UserRepository;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.Transactional;

@Profile("test")
@DataJpaTest
@Import(JpaConfiguration.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class RepositoryTest {

    @Autowired
    protected UserRepository userRepository;

    @Autowired
    protected MentoringRepository mentoringRepository;

    @Autowired
    protected HashTagRepository hashTagRepository;

    @Autowired
    protected CategoryRepository categoryRepository;

    protected UserEntity initializeSavedUser() {
        return userRepository.save(기본유저_엔티티());
    }

    protected MentoringEntity initializeSavedMentoring(final Long userId){
        return mentoringRepository.save(멘토링_등록(userId));
    }

    protected HashTagEntity initializeSavedHashTag(final String name){
        return hashTagRepository.save(new HashTagEntity(name));
    }

    protected CategoryEntity initializeSavedCategory(){
        return categoryRepository.save(toEntity());
    }
}
