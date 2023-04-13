package com.mentors.mentoring.mentoring.service;


import com.mentors.category.CategoryEntity;
import com.mentors.mentoring.dto.AddMentoringRequest;
import com.mentors.mentoring.hashtag.HashTagEntity;
import java.util.List;
import java.util.Set;

public interface MentoringWriteService {

    Long addMentoring(final Long userId,
                      final AddMentoringRequest request,
                      final List<CategoryEntity> categories,
                      final Set<HashTagEntity> hashTags);

    void deleteById(final Long userId, final Long mentoringId);
}
