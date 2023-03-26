package com.mentors.user.service;

import com.mentors.user.UserEntity;
import com.mentors.user.UserRepository;
import com.mentors.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserReadServiceImpl implements UserReadService {

    private final UserRepository userRepository;

    @Override
    public Long signIn(User user,String encodePassword) {
        UserEntity userEntity = userRepository.getByEmail(user.email());
        checkpw(userEntity, encodePassword);
        return userEntity.getId();
    }

    private static void checkpw(UserEntity UserEntity,String encodePassword) {
        if(!UserEntity.getPassword().equals(encodePassword)){
            throw new RuntimeException("인증정보가 일치하지 않습니다.");
        }
    }
}
