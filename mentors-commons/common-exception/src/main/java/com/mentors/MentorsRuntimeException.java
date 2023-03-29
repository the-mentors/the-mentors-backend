package com.mentors;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MentorsRuntimeException extends RuntimeException{

    private final ErrorCode code;
    private final String message;

    public MentorsRuntimeException(ErrorCode code) {
        this.code = code;
        this.message = code.getMessage();
    }

    @Override
    public String getMessage() {
        return message;
    }
}
