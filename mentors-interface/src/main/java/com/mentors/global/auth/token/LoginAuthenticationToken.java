package com.mentors.global.auth.token;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class LoginAuthenticationToken extends AbstractAuthenticationToken {

    private final Object principal;
    private final Object credentials;

    public LoginAuthenticationToken(final Object principal,final Object credentials) {
        super(null);
        super.setAuthenticated(false);
        this.principal = principal;
        this.credentials = credentials;
    }

    public LoginAuthenticationToken(final Object principal,final Object credentials,final Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        super.setAuthenticated(true);
        this.principal = principal;
        this.credentials = credentials;
    }

    @Override
    public Object getCredentials() {
        return this.credentials;
    }

    @Override
    public Object getPrincipal() {
        return this.principal;
    }
}
