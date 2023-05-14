package com.mentors.mentoring.hashtag.service;

import com.mentors.mentoring.hashtag.HashTagEntity;
import java.util.List;

public interface HashTagReadService {

    List<HashTagEntity> findAllByMentoringId(final Long mentoringId);
}
