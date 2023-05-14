package com.mentors.user.auth;

import com.mentors.authority.Authority;
import com.mentors.support.ServiceTest;
import com.mentors.user.user.domain.User;
import com.mentors.user.user.mapper.UserDomainMapper;
import com.mentors.user.user.service.UserWriteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.mentors.UserFixture.*;
import static com.mentors.user.user.mapper.UserDomainMapper.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class UserContextServiceTest extends ServiceTest {

    @Autowired
    private UserContextService userContextService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserWriteService userWriteService;

    @BeforeEach
    void initialize() {
        userWriteService.signUp(toDomain());
    }

    @DisplayName("[Service] 회원가입된 이메일로 정보를 불러오면 유저 정보를 반환한다.")
    @Test
    void giveEmail_whenLoadUserByEmail_thenReturnUserDetails() {
        //given
        final User fixture = toDomainWithRole();

        //when
        var actual = (UserContext) userContextService.loadUserByUsername(fixture.email());
        var actualRoles = convertAuthoritiesToString(actual.getAuthorities());

        //when
        assertAll(() -> {
            assertThat(actual.getUsername()).isEqualTo(fixture.email());
            assertThat(passwordEncoder.matches(fixture.password(), actual.getPassword())).isTrue();
            assertThat(actualRoles).isEqualTo(toDomainWithRole().role());
        });
    }

    @DisplayName("[Service] 회원가입 되지 않은 이메일로 정보를 불러오면 예외를 발생한다.")
    @Test
    void giveUnSavedEmail_whenLoadUserByEmail_thenReturnUserDetails() {
        //given
        String UnSavedEmail = toUpdateUser().email();

        //when & then
        assertThatThrownBy(() -> userContextService.loadUserByUsername(UnSavedEmail))
                .isInstanceOf(RuntimeException.class);
    }

}