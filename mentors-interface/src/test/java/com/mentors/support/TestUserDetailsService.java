package com.mentors.support;

import com.mentors.authority.Authority;
import com.mentors.user.Role;
import com.mentors.user.auth.UserContext;
import com.mentors.user.user.domain.User;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Profile("test")
public class TestUserDetailsService implements UserDetailsService {

    public static final String USERNAME = "user@example.com";
    public static final List<String> ROLE = List.of("ROLE_USER");


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        if (s.equals(USERNAME)) {
            return new UserContext(getUser(),convertStringToAuthorityByRole(ROLE));
        }
        return null;
    }
    private User getUser(){
        return new User(1L, USERNAME, "password", "사용자1","사용자닉네임1", "www.user1.com",ROLE,null,null);
    }

    private static List<Authority> convertStringToAuthorityByRole(List<String> roles) {
        return roles.stream()
                .map(role -> new Authority(Role.findByName(role)))
                .collect(Collectors.toList());
    }

}
