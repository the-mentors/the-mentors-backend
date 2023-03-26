package com.mentors.user.user.service;

import com.mentors.user.user.UserEntity;
import com.mentors.user.user.UserRepository;
import com.mentors.user.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.mentors.user.user.mapper.UserDomainMapper.toEntity;

@Service
@Transactional
@RequiredArgsConstructor
public class UserWriteServiceImpl implements UserWriteService {

    private final UserRepository userRepository;

    @Override
    public Long signUp(User user) {
        checkDuplicateEmail(user);
        var userEntity = toEntity(user);
        return userRepository.save(userEntity).getId();
    }

    private void checkDuplicateEmail(User user) {
        if(userRepository.existsByEmail(user.email())){
            new RuntimeException("이미 존재하는 이메일 입니다");
        }
    }

    @Override
    public void updateUser(final Long userId, final User updateUser){
        final UserEntity user = findUser(userId);
        user.update(toEntity(updateUser));
    }

    private UserEntity findUser(final Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException());
    }
}
