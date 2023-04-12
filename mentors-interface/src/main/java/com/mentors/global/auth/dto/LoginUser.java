package com.mentors.global.auth.dto;

import lombok.Builder;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Builder
public record LoginUser(Long id, String email, String nickname, String profileUrl, ArrayList<String> role, LocalDateTime createDate){
}