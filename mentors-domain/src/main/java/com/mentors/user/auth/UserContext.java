package com.mentors.user.auth;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
public class UserContext extends User {
    private final com.mentors.user.user.domain.User user;

    public UserContext(final com.mentors.user.user.domain.User user, final Collection<? extends GrantedAuthority> authorities) {
        super(user.email(), user.password(), authorities);
        this.user = user;
    }
}
