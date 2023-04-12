package com.mentors.mentoring.dto;

import java.util.List;
import java.util.Set;

public record AddMentoringRequest(String title,
                                  String content,
                                  String thumbnail,
                                  Integer price,
                                  List<String> hashTags,
                                  List<Long> categoryCodes,
                                  List<AddMentoringLinkRequest> links) {
}