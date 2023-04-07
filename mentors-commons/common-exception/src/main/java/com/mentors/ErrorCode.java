package com.mentors;


import lombok.Getter;

@Getter
public enum ErrorCode {

    //JWT
    TOKEN_NOTFOUND_ERROR(404, "인증 토큰이 존재하지 않습니다."),;

    private final int status;
    private final String message;

    ErrorCode(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
