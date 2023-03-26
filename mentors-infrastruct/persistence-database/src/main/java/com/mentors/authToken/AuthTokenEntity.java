package com.mentors.authToken;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PROTECTED;

@Getter
@Entity
@Table(name = "auths")
@NoArgsConstructor(access = PROTECTED)
public class AuthTokenEntity{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "auth_id")
    private Long id;
    private Long userId;
    private String refreshToken;

    @Builder
    public AuthTokenEntity(Long id,Long userId, String refreshToken) {
        this.id = id;
        this.userId = userId;
        this.refreshToken = refreshToken;
    }
}
