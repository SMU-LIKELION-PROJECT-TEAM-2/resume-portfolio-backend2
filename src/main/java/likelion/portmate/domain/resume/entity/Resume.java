package likelion.portmate.domain.resume.entity;

import jakarta.persistence.*;
import likelion.portmate.domain.certification.entity.Certification;
import likelion.portmate.domain.education.entity.Education;
import likelion.portmate.domain.career.entity.Career;
import likelion.portmate.domain.externalActivity.entity.ExternalActivity;
import likelion.portmate.domain.introduction.entity.Introduction;
import likelion.portmate.domain.languageskill.entity.LanguageSkill;
import likelion.portmate.domain.member.entity.Member;
import likelion.portmate.domain.portfolio.entity.Portfolio;
import likelion.portmate.domain.project.entity.Project;
import likelion.portmate.domain.skill.entity.Skill;
import likelion.portmate.global.domain.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "resumes")
@Entity
public class Resume extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String phoneNumber;

    // 단방향 연관관계이므로 mappedBy 제거
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "resume_id") // 외래키 설정
    private List<Skill> skills = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "resume_id")
    private List<Career> careers = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "resume_id")
    private List<Project> projects = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "resume_id")
    private List<Portfolio> portfolios = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "resume_id")
    private List<Education> educations = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "resume_id")
    private List<ExternalActivity> externalActivities = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "resume_id")
    private List<Certification> certifications = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "resume_id")
    private List<LanguageSkill> languageSkills = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "resume_id")
    private List<Introduction> introductions = new ArrayList<>();

    @Builder
    private Resume(
            Member member,
            String email,
            String phoneNumber,
            List<Skill> skills,
            List<Career> careers,
            List<Project> projects,
            List<Portfolio> portfolios,
            List<Education> educations,
            List<ExternalActivity> externalActivities,
            List<Certification> certifications,
            List<LanguageSkill> languageSkills,
            List<Introduction> introductions
    ) {
        this.member = member;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.skills = safeList(skills);
        this.careers = safeList(careers);
        this.projects = safeList(projects);
        this.portfolios = safeList(portfolios);
        this.educations = safeList(educations);
        this.externalActivities = safeList(externalActivities);
        this.certifications = safeList(certifications);
        this.languageSkills = safeList(languageSkills);
        this.introductions = safeList(introductions);
    }

    private <T> List<T> safeList(List<T> list) {
        return list == null ? new ArrayList<>() : list;
    }

}
