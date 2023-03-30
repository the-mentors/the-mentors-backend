package com.mentors.user.user.service;

import com.mentors.user.user.UserEntity;
import com.mentors.user.user.UserRepository;
import com.mentors.user.user.domain.User;
import com.mentors.user.user.mapper.UserDomainMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserReadServiceImpl implements UserReadService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;
    @Override
    public Long signIn(String userEmail,String password) {
        UserEntity userEntity = userRepository.findByEmail(userEmail)
                .orElseThrow(()->new RuntimeException("존재하지 않는 회원입니다."));
        validateCheckPassword(userEntity.getPassword(), password);
        return userEntity.getId();
    }

    @Override
    public User findUserById(Long userId) {
        return userRepository.findById(userId)
                .map(UserDomainMapper::toDomain)
                .orElseThrow(IllegalArgumentException::new);

    }

    private void validateCheckPassword(String dataBasePassword, String requestPassword) {
        if(!passwordEncoder.matches(requestPassword,dataBasePassword)){
            throw new RuntimeException("인증정보가 일치하지 않습니다.");
        }
    }
}
