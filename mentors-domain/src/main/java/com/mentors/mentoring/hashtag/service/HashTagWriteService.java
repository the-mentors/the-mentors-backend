package com.mentors.mentoring.hashtag.service;

import com.mentors.mentoring.hashtag.HashTagEntity;
import java.util.List;
import java.util.Set;

public interface HashTagWriteService {

    Set<Long> saveAllIfDontExist(final List<String> name);

    void deleteNotUsedHashTags(final List<HashTagEntity> hasTags);
}
