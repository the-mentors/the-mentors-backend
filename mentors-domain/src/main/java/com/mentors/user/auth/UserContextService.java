package com.mentors.user.auth;

import com.mentors.authority.Authority;
import com.mentors.user.user.UserRepository;
import com.mentors.user.user.mapper.UserDomainMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.mentors.user.Role.findByName;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserContextService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(final String email) throws UsernameNotFoundException {

        final var findUser = userRepository.findByEmail(email).map(UserDomainMapper::toDomain)
                .orElseThrow(RuntimeException::new);
        final var authorities = convertStringToAuthorityByRole(findUser.role());
        return new UserContext(findUser, authorities);
    }

    private static List<Authority> convertStringToAuthorityByRole(final List<String> roles) {
        return roles.stream()
                .map(role -> new Authority(findByName(role)))
                .collect(Collectors.toList());
    }
}
