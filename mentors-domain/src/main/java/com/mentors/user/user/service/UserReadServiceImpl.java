package com.mentors.user.user.service;

import com.mentors.user.user.UserRepository;
import com.mentors.user.user.domain.User;
import com.mentors.user.user.mapper.UserDomainMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserReadServiceImpl implements UserReadService {

    private final UserRepository userRepository;

    @Override
    public User findUserById(final Long userId) {
        return userRepository.findById(userId)
                .map(UserDomainMapper::toDomain)
                .orElseThrow(IllegalArgumentException::new);

    }

    public void validateExistById(Long userId){
        if (!userRepository.existsById(userId))
            throw new IllegalArgumentException();
    }
}
