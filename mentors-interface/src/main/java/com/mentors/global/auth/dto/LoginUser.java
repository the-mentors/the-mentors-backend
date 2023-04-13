package com.mentors.global.auth.dto;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;

@Builder
public record LoginUser(Long id, String email, String nickname, String profileUrl, List<String> role, LocalDateTime createDate){
}