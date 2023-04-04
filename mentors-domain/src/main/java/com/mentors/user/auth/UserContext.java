package com.mentors.user.auth;

import com.mentors.user.user.domain.User;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;


import java.util.Collection;

@Getter
public class UserContext extends org.springframework.security.core.userdetails.User {
    private final User user;

    public UserContext(final User user, final Collection<? extends GrantedAuthority> authorities) {
        super(user.email(), user.password(), authorities);
        this.user = user;
    }
}
