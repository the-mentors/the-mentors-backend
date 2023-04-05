package com.mentors.global.auth.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;

public record LoginUser(Long id, String email, String nickname, String profileUrl, ArrayList<String> role, LocalDateTime createDate){

}