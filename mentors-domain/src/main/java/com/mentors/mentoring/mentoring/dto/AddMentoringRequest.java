package com.mentors.mentoring.mentoring.dto;

import java.util.List;

public record AddMentoringRequest(String title,
                                  String content,
                                  String thumbnail,
                                  Integer price,
                                  List<String> hashTags,
                                  List<Long> categoryCodes,
                                  List<AddMentoringLinkRequest> links) {
}