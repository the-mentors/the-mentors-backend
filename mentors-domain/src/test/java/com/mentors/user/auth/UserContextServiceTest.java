package com.mentors.user.auth;

import com.mentors.authority.Authority;
import com.mentors.support.ServiceTest;
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
        String email = toDomainWithRole().email();
        //when&&then
        UserDetails actual = userContextService.loadUserByUsername(email);
        String password = toDomainWithRole().password();
        passwordEncoder.matches(password,actual.getPassword());
        ArrayList<String> arr = AuthorityToString(actual.getAuthorities());

        assertAll(() -> {
            assertThat(actual.getUsername()).isEqualTo(email);
            assertThat(passwordEncoder.matches(password,actual.getPassword())).isTrue();
            assertThat(arr).isEqualTo(toDomainWithRole().role());
        });
    }

    @DisplayName("[Service] 회원가입 되지 않은 이메일로 정보를 불러오면 예외를 발생한다.")
    @Test
    void giveUnSavedEmail_whenLoadUserByEmail_thenReturnUserDetails() {
        //given
        String UnSavedEmail = toUpdateUser().email();
        //when&&then
        assertThatThrownBy(() -> userContextService.loadUserByUsername(UnSavedEmail));
    }

    //추후 기능 완성이 끝난후 ArrayList ->List로 변경 예정
    private static ArrayList<String> AuthorityToString(Collection<? extends GrantedAuthority> authorities) {
        List<? extends GrantedAuthority> collect = authorities
                .stream().toList();
        ArrayList<String> arr = new ArrayList<>();
        for (Object role : collect) {
            Authority authority = (Authority) role;
            System.out.println(authority.getRole().toString());
            arr.add(authority.getRole().toString());
        }
        return arr;
    }

}