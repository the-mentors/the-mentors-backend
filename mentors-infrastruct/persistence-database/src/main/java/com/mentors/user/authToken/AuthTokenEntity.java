package com.mentors.user.authToken;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Getter
@Entity
@Table(name = "auths")
@NoArgsConstructor(access = PROTECTED)
public class AuthTokenEntity{

    @Id @GeneratedValue(strategy = IDENTITY)
    @Column(name = "auth_id")
    private Long id;
    private Long keys;
    private String refreshToken;

    @Builder
    public AuthTokenEntity(final Long keys, final String refreshToken) {
        this.keys = keys;
        this.refreshToken = refreshToken;
    }
}
