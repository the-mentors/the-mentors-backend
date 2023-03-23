package com.mentors.user.servuce;

import com.mentors.user.UserRepository;
import com.mentors.user.domain.User;
import com.mentors.user.mapper.UserDomainMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserWriteServiceImpl implements UserWriteService {

    private final UserRepository userRepository;

    @Override
    public void signUp(User user) {
        checkDuplicateEmail(user);
        var userEntity = UserDomainMapper.toEntity(user);
        userRepository.save(userEntity);

    }
    //차후 Exception을 Global로 통합하여 DuplicateUserException()으로 예외변경 예정
    private void checkDuplicateEmail(User user) {
        if(userRepository.existsByEmail(user.email())){
            new RuntimeException("이미 존재하는 이메일 입니다");
        }
    }
}
