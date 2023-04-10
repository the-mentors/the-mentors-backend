package com.mentors.mentoring.hashtag.service;

import java.util.List;
import java.util.Set;

public interface HashTagWriteService {

    Set<Long> saveAllIfDontExist(final List<String> name);
}
