package com.mentors.global.error;

import static com.mentors.global.error.ErrorResponse.error;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

import com.mentors.MentorsRuntimeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ApiControllerAdvice {


    @ExceptionHandler(MentorsRuntimeException.class)
    public ResponseEntity<?> applicationHandler(MentorsRuntimeException e){
        log.error("[Error]  {} ", e.getMessage());
        return ResponseEntity.status(e.getCode().getStatus())
                .body(error(e.getMessage()));
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> applicationHandler(RuntimeException e){
        log.error("[Error] Internal Server Error ", e);
        return ResponseEntity.status(INTERNAL_SERVER_ERROR)
                .body(error(INTERNAL_SERVER_ERROR.name()));
    }
}
