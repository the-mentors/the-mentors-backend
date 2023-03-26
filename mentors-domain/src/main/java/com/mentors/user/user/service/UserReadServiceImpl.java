package com.mentors.user.user.service;

import com.mentors.user.user.UserEntity;
import com.mentors.user.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserReadServiceImpl implements UserReadService {

    private final UserRepository userRepository;

    @Override
    public Long signIn(String userEmail,String encodePassword) {
        UserEntity userEntity = userRepository.findByEmail(userEmail)
                .orElseThrow(()->new RuntimeException("존재하지 않는 회원입니다."));
        validateCheckPassword(userEntity.getPassword(), encodePassword);
        return userEntity.getId();
    }

    private static void validateCheckPassword(String password, String encodePassword) {
        if(!password.equals(encodePassword)){
            throw new RuntimeException("인증정보가 일치하지 않습니다.");
        }
    }
}
