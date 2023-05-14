package com.mentors.user.user.service;

import com.mentors.user.user.UserEntity;
import com.mentors.user.user.UserRepository;
import com.mentors.user.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.mentors.user.user.mapper.UserDomainMapper.toEntity;
import static com.mentors.user.user.mapper.UserDomainMapper.toEntityWithRoleUser;

@Service
@Transactional
@RequiredArgsConstructor
public class UserWriteServiceImpl implements UserWriteService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public Long signUp(final User user) {
        checkDuplicateEmail(user.email());
        final var userEntity = createUser(user);
        return userRepository.save(userEntity).getId();
    }

    private UserEntity createUser(final User user) {
        final var password = passwordEncoder.encode(user.password());
        return toEntityWithRoleUser(user,password);
    }

    private void checkDuplicateEmail(final String email) {
        if(userRepository.existsByEmail(email)){
            throw new RuntimeException("이미 존재하는 이메일 입니다");
        }
    }

    @Override
    public void updateUser(final Long userId, final User updateUser){
        final var user = findUser(userId);
        user.update(toEntity(updateUser));
    }

    private UserEntity findUser(final Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("존재하지않는 유저입니다."));
    }
}
