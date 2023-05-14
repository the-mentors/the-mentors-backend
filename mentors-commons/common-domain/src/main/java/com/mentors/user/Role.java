package com.mentors.user;

import java.util.Arrays;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Role {
    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN"),
    ANONYMOUS("ROLE_ANONYMOUS");

    private final String role;

    public static Role findByName(final String name){
        return Arrays.stream(values())
                .filter(value -> value.isSameName(name))
                .findFirst()
                .orElseThrow();
    }

    private boolean isSameName(final String name) {
        return this.role.equals(name);
    }
}
