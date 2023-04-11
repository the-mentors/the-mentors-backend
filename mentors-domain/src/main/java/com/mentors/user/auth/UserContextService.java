package com.mentors.user.auth;

import com.mentors.authority.Authority;
import com.mentors.user.Role;
import com.mentors.user.user.UserRepository;
import com.mentors.user.user.domain.User;
import com.mentors.user.user.mapper.UserDomainMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserContextService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(final String email) throws UsernameNotFoundException {

        final User findUser = userRepository.findByEmail(email).map(UserDomainMapper::toDomain)
                .orElseThrow(RuntimeException::new);
        ArrayList<Authority> authorities = changeStringToAuthorityByRole(findUser.role());
        return new UserContext(findUser, authorities);
    }

    private static ArrayList<Authority> changeStringToAuthorityByRole(ArrayList<String> roles) {
        ArrayList<Authority> authorities = new ArrayList<>();
        roles.forEach(role->authorities.add(new Authority(Enum.valueOf(Role.class, role))));
        return authorities;
    }
}
