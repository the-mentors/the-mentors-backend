package com.mentors.auth;

import com.mentors.global.common.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PROTECTED;

@Getter
@Entity
@Table(name = "auths")
@NoArgsConstructor(access = PROTECTED)
public class AuthEntity extends BaseEntity {
    @Id
    private Long memberId;

    private String refreshToken;


    @Builder
    public AuthEntity(Long memberId, String refreshToken) {
        this.memberId = memberId;
        this.refreshToken = refreshToken;
    }

    public static AuthEntity of(final Long memberId,
                                final String refreshToken) {
       return AuthEntity.builder()
                .memberId(memberId)
                .refreshToken(refreshToken)
                .build();
    }
}
