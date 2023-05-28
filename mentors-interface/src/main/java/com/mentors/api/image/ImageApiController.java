package com.mentors.api.image;

import com.mentors.anotation.ExtensionValid;
import com.mentors.dto.ImageRequest;
import com.mentors.dto.ImageResponses;
import com.mentors.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/storage")
public class ImageApiController {

    private final ImageService imageService;

    @PostMapping
    public ResponseEntity<ImageResponses> upload(@ExtensionValid ImageRequest request) {
        ImageResponses responses = imageService.upload(request.files());
        return ResponseEntity.ok(responses);
    }
}
