package com.mentors.mentoring;

import lombok.Getter;

@Getter
public enum LinkType {

    ZOOM("줌"),
    DISCORD("디스코드"),
    KAKAO_TALK("카카오톡"),
    GITHUB("깃허브"),
    OTHER("그 외");

    private final String name;

    LinkType(String name) {
        this.name = name;
    }
}