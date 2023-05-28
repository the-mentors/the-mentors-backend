package com.mentors.dto;

public record ImageResponse(String fileUrl, String originName) {
    public static ImageResponse of(String fileUrl, String originName){
        return new ImageResponse(fileUrl, originName);
    }
}
