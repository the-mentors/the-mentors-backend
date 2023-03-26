package com.mentors.user.mapper;

import com.mentors.support.BasicClassTest;
import com.mentors.user.UserEntity;
import com.mentors.user.mapper.UserDomainMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.mentors.UserFixture.toDomain;


public class UserDomainMapperTest extends BasicClassTest {
    @DisplayName("유저 도메인을 엔티티로 변환")
    @Test
    void givenUserDomain_whenTransformingDomain_thenReturnUserEntity() {
        //given & when
        UserEntity entity = UserDomainMapper.toEntity(toDomain());

        //then
        Assertions.assertInstanceOf(UserEntity.class,entity);
    }
    //추후 엔티티에서 도메인 변경 로직 추가시 테스트 추가하겠습니다.
}
