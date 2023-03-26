package com.mentors.authToken;

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
public class AuthTokenEntity extends BaseEntity {
    @Id
    private Long memberId;

    private String refreshToken;


    @Builder
    public AuthTokenEntity(Long memberId, String refreshToken) {
        this.memberId = memberId;
        this.refreshToken = refreshToken;
    }

    public static AuthTokenEntity of(final Long memberId,
                                     final String refreshToken) {
       return AuthTokenEntity.builder()
                .memberId(memberId)
                .refreshToken(refreshToken)
                .build();
    }
}
