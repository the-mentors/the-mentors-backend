package com.mentors.service;

import com.mentors.dto.ImageResponse;
import com.mentors.dto.ImageResponses;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class ImageService {
    private final StoreService storeService;

    public ImageResponses upload(List<MultipartFile> files) {
        List<ImageResponse> images = files.stream()
                .map(storeService::store)
                .collect(Collectors.toList());

        return ImageResponses.of(images);
    }
}
