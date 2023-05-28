package com.mentors.service;

import com.google.cloud.storage.Bucket;
import com.google.firebase.cloud.StorageClient;
import com.mentors.config.FireStorageProperties;
import com.mentors.dto.ImageResponse;
import java.io.IOException;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService{

    private final FireStorageProperties properties;
    @Override
    public ImageResponse store(MultipartFile file) {
        try {
            Bucket bucket = StorageClient.getInstance().bucket();
            String name = generateUniqueFileName();
            bucket.create(name, file.getBytes(), file.getContentType());
            return new ImageResponse(getImageUrl(name), file.getOriginalFilename());
        }catch (IOException e){
            throw new IllegalArgumentException();
        }
    }

    public String getImageUrl(String name) {
        return String.format(properties.getImageUrl(), name);
    }

    private String generateUniqueFileName(){
        return UUID.randomUUID().toString();
    }
}
