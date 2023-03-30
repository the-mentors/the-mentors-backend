package com.mentors.authority;


import com.mentors.global.common.Role;
import com.mentors.user.user.UserEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.FetchType.LAZY;
import static lombok.AccessLevel.PROTECTED;

@Getter
@Entity
@Table(name = "authorities")
@NoArgsConstructor(access = PROTECTED)
public class Authority implements GrantedAuthority {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "authority_id")
    private Long id;

    @Enumerated(value = STRING)
    private Role role;

    public Authority(Role role) {
        this.role = role;
    }

    public String getAuthority() {
        return role.getRole();
    }

    @ManyToMany(fetch = LAZY, mappedBy = "userRoles")
    private Set<UserEntity> users = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Authority authority = (Authority) o;
        return role == authority.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(role);
    }
}