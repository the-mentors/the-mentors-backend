package com.mentors.user.user;

import com.mentors.authority.Authority;
import com.mentors.global.common.BaseEntity;
import com.mentors.user.Role;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.*;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.FetchType.LAZY;
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

    @ManyToMany(fetch = LAZY, cascade = {ALL})
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "authority_id"))
    private final Set<Authority> userRoles = new HashSet<>();

    @Builder
    public UserEntity(Long id, String email, String password, String username, String nickname, String profileUrl, String role) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.username = username;
        this.nickname = nickname;
        this.profileUrl = profileUrl;
        this.userRoles.add(new Authority(Enum.valueOf(Role.class,role)));

    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>(userRoles);
    }


    public void update(final UserEntity updateUser) {
        updateUsername(updateUser.username);
        updateNickname(updateUser.nickname);
        updateProfileUrl(updateUser.profileUrl);
    }

    private void updateUsername(final String username) {
        if (username != null) {
            this.username = username;
        }
    }

    private void updateNickname(final String nickname) {
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
