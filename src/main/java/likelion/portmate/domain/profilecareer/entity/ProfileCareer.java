package likelion.portmate.domain.profilecareer.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import likelion.portmate.domain.member.entity.Member;
import likelion.portmate.global.domain.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class ProfileCareer extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "member_id")
    private Member member;

    @Column(nullable = false) private String title;

    @Column(columnDefinition = "TEXT") private String description;

    @Column(nullable = false) private LocalDate startDate;

    private LocalDate endDate;

    @Builder
    public ProfileCareer(Member member, String title, String description,
                         LocalDate startDate, LocalDate endDate) {
        this.member  = member;
        this.title   = title;
        this.description  = description;
        this.startDate = startDate;
        this.endDate   = endDate;
    }

    public void update(String title, String description, LocalDate startDate, LocalDate endDate) {
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
    }

}