package com.mentors.user.authToken;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthTokenRepository extends JpaRepository<AuthTokenEntity, Long> {
    Optional<AuthTokenEntity> findByKeys(final Long key);

    void deleteByKeys(final Long key);
}

