package com.mentors.user.authToken.service;

import com.mentors.support.ServiceTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.mentors.AuthFixture.refreshToken;
import static com.mentors.AuthFixture.unSavedKey;
import static com.mentors.AuthFixture.key;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AuthServiceTest extends ServiceTest {

    @Autowired
    private AuthService authService;

    @BeforeEach
    void initialize() {
        authService.saveAuthToken(key, refreshToken);
    }

    @DisplayName("[Service] 키로 인증 토큰을 찾으면 인증토큰을 반환한다.")
    @Test
    void giveKey_whenGetAuthTokenByKey_thenReturnAuthToken() {
        //given&&when
        String actual = authService.getAuthToken(key);

        //then
        assertThat(actual).isEqualTo(refreshToken);

    }

    @DisplayName("[Service] 존재하지 않는 키로 인증 토큰을 찾으면 예외를 반환한다.")
    @Test
    void giveUnSavedKey_whenGetAuthTokenByKey_thenReturnThrows() {
        //given&&when&&then
        assertThatThrownBy(() -> authService.getAuthToken(unSavedKey))
                .isInstanceOf(RuntimeException.class);

    }

    @DisplayName("[Service] 키가 존재한다면 참을 반환한다.")
    @Test
    void giveKey_whenExistAuthTokenByKey_thenReturnTrue() {
        //given&&when&&then
        assertThat(authService.existAuthToken(key)).isTrue();

    }

    @DisplayName("[Service] 키가 존재하지 않다면 거짓을 반환한다.")
    @Test
    void giveUnSavedKey_whenExistAuthTokenByKey_thenReturnFalse() {
        //given&&when&&then
        assertThat(authService.existAuthToken(unSavedKey)).isFalse();
    }

    @DisplayName("[Service] 키가 이미 저장되어있는 경우 삭제한다.")
    @Test
    void giveKey_whenIfExistAuthTokenDelete_thenReturn() {
        //given
        assertThat(authService.getAuthToken(key)).isEqualTo(refreshToken);

        //when
        authService.ifExistAuthTokenDelete(key);

        //then
        assertThatThrownBy(() -> authService.getAuthToken(key))
                .isInstanceOf(RuntimeException.class);
    }

}
