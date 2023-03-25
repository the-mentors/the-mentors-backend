package com.mentors.user.service;

import static com.mentors.user.mapper.UserDomainMapper.*;

import com.mentors.user.UserEntity;
import com.mentors.user.UserRepository;
import com.mentors.user.domain.User;
import com.mentors.user.mapper.UserDomainMapper;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

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

    public void updateUser(final Long userId, final User updateUser){
        userRepository.findById(userId)
                .ifPresentOrElse(
                        user -> user.update(toEntity(updateUser)),
                        () -> new RuntimeException());
    }

    private UserEntity findUser(final Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException());
    }
}
