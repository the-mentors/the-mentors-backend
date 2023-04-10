package com.mentors.api.mentoring.mentoring.dto;

import com.mentors.mentoring.dto.AddMentoringCommand.MentoringLinkCommand;
import java.util.List;

public record AddMentoringRequest(String title,
                                  String content,
                                  Integer price,
                                  String thumbnail,
                                  List<String> hashTags,
                                  List<Long> categoryCodes,
                                  List<MentoringLinkCommand> links) {
}

