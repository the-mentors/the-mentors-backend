package com.mentors.dto;

import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public record ImageRequest(List<MultipartFile> files) {
    public static ImageRequest of(List<MultipartFile> files) {
        return new ImageRequest(files);
    }
}
