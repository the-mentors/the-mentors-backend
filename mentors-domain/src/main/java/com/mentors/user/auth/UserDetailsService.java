package com.mentors.user.auth;

import com.mentors.user.user.UserRepository;
import com.mentors.user.user.domain.User;
import com.mentors.user.user.mapper.UserDomainMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(final String email) throws UsernameNotFoundException {

        final User findUser = userRepository.findByEmail(email).map(UserDomainMapper::toDomain)
                .orElseThrow(RuntimeException::new);


        return new UserContext(findUser, findUser.role());
    }
}
