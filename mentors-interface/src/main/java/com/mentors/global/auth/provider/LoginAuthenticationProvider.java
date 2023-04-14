package com.mentors.global.auth.provider;

import com.mentors.api.user.dto.UserSignInRequest;
import com.mentors.global.auth.dto.LoginUser;
import com.mentors.global.auth.token.LoginAuthenticationToken;
import com.mentors.user.auth.UserContext;
import com.mentors.user.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
public class LoginAuthenticationProvider implements AuthenticationProvider {

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(final Authentication authentication) throws AuthenticationException {
        final var principal = (UserSignInRequest) authentication.getPrincipal();
        final var password = (String) authentication.getCredentials();

        final var userContext = (UserContext) userDetailsService.loadUserByUsername(principal.email());
        if (isNotMatchPassword(password, userContext)) {
            throw new BadCredentialsException("");
        }
        final var loginUser = userContextUserToLoginUser(userContext.getUser());
        return new LoginAuthenticationToken(loginUser, null, userContext.getAuthorities());
    }

    private static LoginUser userContextUserToLoginUser(final User user) {
        return  LoginUser.builder()
                .id(user.id())
                .email(user.email())
                .nickname(user.nickname())
                .profileUrl(user.profileUrl())
                .role(user.role())
                .createDate(user.createdAt())
                .build();
    }

    private boolean isNotMatchPassword(final String password,final UserContext userContext) {
        return !passwordEncoder.matches(password, userContext.getPassword());
    }

    @Override
    public boolean supports(final Class<?> authentication) {
        return authentication.equals(LoginAuthenticationToken.class);
    }
}
