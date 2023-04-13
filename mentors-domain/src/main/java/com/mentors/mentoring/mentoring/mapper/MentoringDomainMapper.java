package com.mentors.mentoring.mentoring.mapper;

import static com.mentors.mentoring.LinkType.findById;

import com.mentors.category.CategoryEntity;
import com.mentors.mentoring.category.MentoringCategoryEntity;
import com.mentors.mentoring.dto.AddMentoringLinkRequest;
import com.mentors.mentoring.dto.AddMentoringRequest;
import com.mentors.mentoring.mentoring.MentoringEntity;
import com.mentors.mentoring.mentoring.MentoringLinkEntity;
import java.util.List;
import java.util.stream.Collectors;

public class MentoringDomainMapper {

    public static MentoringEntity toEntity(final Long userId, final AddMentoringRequest request){
        return MentoringEntity.builder()
                .userId(userId)
                .title(request.title())
                .content(request.content())
                .price(request.price())
                .thumbnail(request.thumbnail())
                .build();
    }

    public static List<MentoringLinkEntity> toLinkEntities(final List<AddMentoringLinkRequest> requests){
        return requests.stream()
                .map(MentoringDomainMapper::toLinkEntity)
                .collect(Collectors.toList());
    }

    private static MentoringLinkEntity toLinkEntity(AddMentoringLinkRequest request){
        return MentoringLinkEntity.of(findById(request.type()), request.url());
    }
}
