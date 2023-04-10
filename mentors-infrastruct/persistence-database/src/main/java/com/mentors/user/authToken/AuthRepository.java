package com.mentors.user.authToken;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthRepository extends JpaRepository<AuthEntity, Long> {
    Optional<AuthEntity> findByKeys(final Long key);

    void deleteByKeys(final Long key);

    boolean existsByKeys(final Long key);
}

