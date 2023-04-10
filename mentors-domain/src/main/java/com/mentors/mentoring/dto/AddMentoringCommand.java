package com.mentors.mentoring.dto;

import java.util.List;
import java.util.Set;


public record AddMentoringCommand(String title,
                                  String content,
                                  String thumbnail,
                                  Integer price,
                                  Set<Long> hashTags,
                                  List<Long> categories,
                                  List<MentoringLinkCommand> links) {

    public record MentoringLinkCommand(Long type,
                                       String url) {
    }
}

