package com.mentors.mypage.domain;

import com.mentors.mentoring.LinkType;
import com.mentors.mentoring.mentoring.MentoringEntity;
import com.mentors.mypage.dto.MentoringMyPageResponse;

import java.util.List;
import java.util.stream.Collectors;

public record MentoringLinkMyPageResponse(Long id,
                                          String url,
                                          LinkType linkType) {
    public static List<MentoringLinkMyPageResponse> toDto(final MentoringEntity mentoring) {
        return mentoring.
                getLinks()
                .stream()
                .map(m -> new MentoringLinkMyPageResponse(m.getId(), m.getLinkUrl(), m.getLinkType()))
                .collect(Collectors.toList());
    }
}
