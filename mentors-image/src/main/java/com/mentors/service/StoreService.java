package com.mentors.service;


import com.mentors.dto.ImageResponse;
import org.springframework.web.multipart.MultipartFile;

public interface StoreService {

    ImageResponse store(MultipartFile file);
}
