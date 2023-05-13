package com.mentors.mypage;


import com.mentors.global.common.BaseEntity;
import com.mentors.mentoring.mentoring.MentoringEntity;
import com.mentors.user.user.UserEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.LAZY;
import static lombok.AccessLevel.PROTECTED;

@Getter
@Entity
@Table(name = "mypages")
@NoArgsConstructor(access = PROTECTED)
public class MyPageEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "myPage_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "mentor_id", nullable = false)
    private UserEntity mentor;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "mentee_id", nullable = false)
    private UserEntity mentee;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "mentoring_id", nullable = false)
    private MentoringEntity mentoring;

    @Builder
    public MyPageEntity(Long id, UserEntity mentor, UserEntity mentee, MentoringEntity mentoring) {
        this.id = id;
        this.mentor = mentor;
        this.mentee = mentee;
        this.mentoring = mentoring;
    }

}
