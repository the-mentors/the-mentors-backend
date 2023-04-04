package com.mentors.global.auth.utils;

public enum AuthorizationType {
    BASIC,
    BEARER;

    public String toLowerCase() {
        return this.name().toLowerCase();
    }
}