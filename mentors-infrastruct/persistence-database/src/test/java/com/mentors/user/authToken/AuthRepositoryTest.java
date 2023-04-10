package com.mentors.user.authToken;

import com.mentors.support.RepositoryTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.mentors.AuthEntityFixture.기본토큰생성;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;


public class AuthRepositoryTest extends RepositoryTest {

    @Autowired
    private AuthRepository authRepository;

    @DisplayName("[Repository] 키로 인증 찾으면 인증값을 반환한다")
    @Test
    void giveAuthKey_whenFindAuthByKey_thenReturnAuthKey() {
        //given
        Long key = initializeAuthTokenSave();

        //when
        AuthEntity actual = authRepository.findByKeys(key).get();
        //then

        assertAll(() -> {
            assertThat(actual.getKeys()).isEqualTo(key);
        });
    }

    @DisplayName("[Repository] 저장되어있지 않은 키로 인증 조회시, 빈값을 반환한다.")
    @Test
    void giveUnSavedAuthKey_whenFindAuthByKey_thenNull() {
        //given
        initializeAuthTokenSave();
        Long unSavedAuthTokenKey = 0L;
        //when && then
        assertThat(authRepository.findByKeys(unSavedAuthTokenKey)).isEmpty();

    }

    @DisplayName("[Repository] 키로 인증 삭제한다.")
    @Test
    void giveAuthKey_whenDeleteAuthByKey_thenDelete() {
        //given
        Long key = initializeAuthTokenSave();
        //when
        AuthEntity actual = authRepository.findByKeys(key).get();
        assertAll(() -> {
            assertThat(actual.getKeys()).isEqualTo(key);
        });
        authRepository.deleteByKeys(key);

        //then
        assertThat(authRepository.findByKeys(key)).isEmpty();

    }

    @DisplayName("[Repository] 키가 존재한다면 참을 반환하고 존재하지 않다면 거짓을 반환한다.")
    @Test
    void giveAuthKey_whenExistsByKeys_thenDelete() {
        //given
        Long key = initializeAuthTokenSave();
        //when&&then
        assertThat(authRepository.existsByKeys(key)).isTrue();
    }

    @DisplayName("[Repository] 키가 존재한다면 참을 반환하고 존재하지 않다면 거짓을 반환한다.")
    @Test
    void giveUnSavedAuthKey_whenExistsByKeys_thenDelete() {
        //given
        Long unSavedKey = 0L;
        //when&&then
        assertThat(authRepository.existsByKeys(unSavedKey)).isFalse();

    }


    private Long initializeAuthTokenSave() {
        return authRepository.save(기본토큰생성()).getKeys();
    }


}
