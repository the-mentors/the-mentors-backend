package com.mentors.user;

import com.mentors.global.common.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Getter
@Entity
@Table(name = "users")
@NoArgsConstructor(access = PROTECTED)
public class UserEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "email", unique = true)
    private String email;

    private String password;
    private String username;
    @Column(length = 14)
    private String nickname;
    private String profileUrl;


    private String role;

    @Builder
    public UserEntity(Long id, String email, String password, String username, String nickname, String profileUrl, String role) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.username = username;
        this.nickname = nickname;
        this.profileUrl = profileUrl;
        this.role = role;

    }

    public void update(final UserEntity updateUser){
        updateUsername(updateUser.username);
        updateNickname(updateUser.nickname);
        updateProfileUrl(updateUser.profileUrl);
    }

    private void updateUsername(final String username) {
        if (username != null) {
            this.username = username;
        }
    }

    private void updateNickname(final String nickname){
        if (nickname != null) {
            this.nickname = nickname;
        }
    }

    private void updateProfileUrl(String profileUrl) {
        if (profileUrl != null) {
            this.profileUrl = profileUrl;
        }
    }

    public boolean isSameId(final Long id) {
        return Objects.equals(id, this.id);
    }
}
