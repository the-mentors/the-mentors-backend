package com.mentors.mentoring.mentoring.mapper;

import static com.mentors.mentoring.LinkType.findById;

import com.mentors.mentoring.mentoring.dto.AddMentoringLinkRequest;
import com.mentors.mentoring.mentoring.dto.AddMentoringRequest;
import com.mentors.mentoring.mentoring.MentoringEntity;
import com.mentors.mentoring.mentoring.MentoringLinkEntity;
import com.mentors.user.user.UserEntity;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class MentoringDomainMapper {

    public static MentoringEntity toEntity(final UserEntity user, final AddMentoringRequest request){
        return MentoringEntity.builder()
                .user(user)
                .title(request.title())
                .content(request.content())
                .price(request.price())
                .thumbnail(request.thumbnail())
                .rating(0)
                .build();
    }
    public static List<MentoringLinkEntity> toLinkEntities(final List<AddMentoringLinkRequest> requests) {
        if (Objects.isNull(requests)) return Collections.emptyList();
        return requests.stream()
                .map(MentoringDomainMapper::toLinkEntity)
                .collect(Collectors.toList());
    }

    private static MentoringLinkEntity toLinkEntity(AddMentoringLinkRequest request){
        return MentoringLinkEntity.of(findById(request.type()), request.url());
    }
}
