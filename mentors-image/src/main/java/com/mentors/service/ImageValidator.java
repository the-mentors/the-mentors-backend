package com.mentors.service;


import org.springframework.web.multipart.MultipartFile;

public interface ImageValidator {

    void validate(MultipartFile multipartFile);
}
