package com.mentors.global.auth.dto;

import lombok.Builder;
import java.time.LocalDateTime;
import java.util.List;

@Builder
public record LoginUser(Long id, String email, String nickname, String profileUrl, List<String> role, LocalDateTime createDate){
}