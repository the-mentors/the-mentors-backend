package com.mentors.mentoring;

import java.util.Arrays;
import lombok.Getter;

@Getter
public enum LinkType {

    ZOOM(1L, "줌"),
    DISCORD(2L, "디스코드"),
    KAKAO_TALK(3L,"카카오톡"),
    GITHUB(4L, "깃허브"),
    OTHER(5L, "그 외");

    private final Long id;
    private final String name;

    LinkType(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static LinkType findById(final Long id){
        return Arrays.stream(values())
                .filter(value -> value.existById(id))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    private boolean existById(final Long id) {
        return this.id.equals(id);
    }
}