package com.mentors.mentoring.hashtag;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HashTagRepository extends JpaRepository<HashTagEntity, Long> {

    Optional<HashTagEntity> findByNameValue(final String value);

}
