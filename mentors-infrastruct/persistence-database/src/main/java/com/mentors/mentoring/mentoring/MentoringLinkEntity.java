package com.mentors.mentoring.mentoring;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

import com.mentors.mentoring.LinkType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = PROTECTED)
@Table(name = "mentoring_links")
public class MentoringLinkEntity {

    @Id @GeneratedValue(strategy = IDENTITY)
    @Column(name = "mentoring_link_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mentoring_id")
    private MentoringEntity mentoring;

    @Enumerated(EnumType.STRING)
    private LinkType linkType;

    @Column(name = "link_url", nullable = false)
    private String linkUrl;

    @Builder
    public MentoringLinkEntity(final MentoringEntity mentoring,
                               final LinkType linkType,
                               final String linkUrl) {
        this.mentoring = mentoring;
        this.linkType = linkType;
        this.linkUrl = linkUrl;
    }

    public static MentoringLinkEntity of(LinkType linkType, String linkUrl){
        return MentoringLinkEntity.builder()
                .linkType(linkType)
                .linkUrl(linkUrl)
                .build();
    }

    public void addMentoring(MentoringEntity mentoringEntity) {
        this.mentoring = mentoringEntity;
    }
}
