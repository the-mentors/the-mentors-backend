package com.mentors.user.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    boolean existsByEmail(final String email);

    Optional<UserEntity>  findByEmail(final String email);
}
