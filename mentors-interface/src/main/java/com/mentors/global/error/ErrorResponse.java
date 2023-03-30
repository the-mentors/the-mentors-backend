package com.mentors.global.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorResponse<T> {

    private final String resultCode;

    public static ErrorResponse<Void> error(String errorCode){
        return new ErrorResponse<>(errorCode);
    }

    public String toStream(){
        return "{" +
                "\"resultCode\" : " + "\"" + resultCode + "\"," +
                "\"result\" : " + null + "}";

    }
}
