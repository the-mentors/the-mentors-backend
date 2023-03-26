package com.mentors.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    boolean existsByEmail(String email);

    Optional<UserEntity>  findByEmail(String email);

    default UserEntity getByEmail(final String email){
        return findByEmail(email)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 회원입니다."));
    }
}
