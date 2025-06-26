package likelion.portmate.domain.resume.service;

import likelion.portmate.domain.career.dto.request.CareerSaveRequest;
import likelion.portmate.domain.career.entity.Career;
import likelion.portmate.domain.certification.dto.request.CertificationSaveRequest;
import likelion.portmate.domain.certification.entity.Certification;
import likelion.portmate.domain.education.dto.request.EducationSaveRequest;
import likelion.portmate.domain.education.entity.Education;
import likelion.portmate.domain.externalActivity.dto.request.ExternalActivitySaveRequest;
import likelion.portmate.domain.externalActivity.entity.ExternalActivity;
import likelion.portmate.domain.introduction.dto.request.IntroductionSaveRequest;
import likelion.portmate.domain.introduction.entity.Introduction;
import likelion.portmate.domain.languageskill.dto.request.LanguageSkillSaveRequest;
import likelion.portmate.domain.languageskill.entity.LanguageSkill;
import likelion.portmate.domain.member.controller.exception.MemberNotFoundException;
import likelion.portmate.domain.member.entity.Member;
import likelion.portmate.domain.member.entity.MemberRepository;
import likelion.portmate.domain.portfolio.dto.request.PortfolioSaveRequest;
import likelion.portmate.domain.portfolio.entity.Portfolio;
import likelion.portmate.domain.project.dto.requeset.ProjectSaveRequest;
import likelion.portmate.domain.project.entity.Project;
import likelion.portmate.domain.resume.dto.request.ResumeSaveRequest;
import likelion.portmate.domain.resume.entity.Resume;
import likelion.portmate.domain.resume.entity.ResumeRepository;
import likelion.portmate.domain.skill.dto.request.SkillSaveRequest;
import likelion.portmate.domain.skill.entity.Skill;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ResumeService {

    private final MemberRepository memberRepository;
    private final ResumeRepository resumeRepository;

    @Transactional
    public void save(Long memberId, ResumeSaveRequest request) {
        Member member = memberRepository.findByMemberId(memberId)
                .orElseThrow(MemberNotFoundException::new);

        Resume resume = Resume.builder()
                .member(member)
                .email(request.email())
                .phoneNumber(request.phoneNumber())
                .build();

        addSkills(resume, request.skills());
        addCareers(resume, request.careers());
        addProjects(resume, request.projects());
        addPortfolios(resume, request.portfolios());
        addEducations(resume, request.educations());
        addExternalActivities(resume, request.externalActivities());
        addCertifications(resume, request.certifications());
        addLanguageSkills(resume, request.languageSkills());
        addIntroductions(resume, request.introductions());

        resumeRepository.save(resume);
    }

    private void addSkills(Resume resume, List<SkillSaveRequest> skillSaveRequests) {
        if (skillSaveRequests == null) return;
        skillSaveRequests.forEach(dto -> {
            Skill skill = Skill.builder()
                    .favoriteSkillEng(dto.favoriteSkillEng())
                    .favoriteSkillKor(dto.favoriteSkillKor())
                    .build();
            resume.getSkills().add(skill);
        });
    }

    private void addCareers(Resume resume, List<CareerSaveRequest> careerSaveRequests) {
        if (careerSaveRequests == null) return;
        careerSaveRequests.forEach(dto -> {
            Career career = Career.builder()
                    .companyName(dto.companyName())
                    .startDate(dto.startDate())
                    .endDate(dto.endDate())
                    .position(dto.position())
                    .departmentName(dto.departmentName())
                    .workType(dto.workType())
                    .responsibilities(dto.responsibilities())
                    .build();
            resume.getCareers().add(career);
        });
    }

    private void addProjects(Resume resume, List<ProjectSaveRequest> projectSaveRequests) {
        if (projectSaveRequests == null) return;
        projectSaveRequests.forEach(dto -> {
            Project project = Project.builder()
                    .projectName(dto.projectName())
                    .organization(dto.organization())
                    .startDate(dto.startDate())
                    .endDate(dto.endDate())
                    .description(dto.description())
                    .build();
            resume.getProjects().add(project);
        });
    }

    private void addPortfolios(Resume resume, List<PortfolioSaveRequest> portfolioSaveRequests) {
        if (portfolioSaveRequests == null) return;
        portfolioSaveRequests.forEach(dto -> {
            Portfolio portfolio = Portfolio.builder()
                    .portfolioUrl(dto.portfolioUrl())
                    .build();
            resume.getPortfolios().add(portfolio);
        });
    }

    private void addEducations(Resume resume, List<EducationSaveRequest> educationSaveRequests) {
        if (educationSaveRequests == null) return;
        educationSaveRequests.forEach(dto -> {
            Education education = Education.builder()
                    .educationType(dto.educationType())
                    .organization(dto.organization())
                    .majorName(dto.majorName())
                    .majorCategory(dto.majorCategory())
                    .startDate(dto.startDate())
                    .endDate(dto.endDate())
                    .enrollmentStatus(dto.enrollmentStatus())
                    .build();
            resume.getEducations().add(education);
        });
    }


    private void addExternalActivities(Resume resume, List<ExternalActivitySaveRequest> externalActivitySaveRequests) {
        if (externalActivitySaveRequests == null) return;
        externalActivitySaveRequests.forEach(dto -> {
            ExternalActivity externalActivity = ExternalActivity.builder()
                    .activityName(dto.activityName())
                    .affiliation(dto.affiliation())
                    .activityOrganization(dto.activityOrganization())
                    .description(dto.description())
                    .build();
            resume.getExternalActivities().add(externalActivity);
        });
    }

    private void addCertifications(Resume resume, List<CertificationSaveRequest> certificationSaveRequests) {
        if (certificationSaveRequests == null) return;
        certificationSaveRequests.forEach(dto -> {
            Certification certification = Certification.builder()
                    .name(dto.name())
                    .scoreOrGrade(dto.scoreOrGrade())
                    .issuingOrganization(dto.issuingOrganization())
                    .acquisitionDate(dto.acquisitionDate())
                    .build();
            resume.getCertifications().add(certification);
        });
    }

    private void addLanguageSkills(Resume resume, List<LanguageSkillSaveRequest> languageSkillSaveRequests) {
        if (languageSkillSaveRequests == null) return;
        languageSkillSaveRequests.forEach(dto -> {
            LanguageSkill languageSkill = LanguageSkill.builder()
                    .languageName(dto.languageName())
                    .proficiencyLevel(dto.proficiencyLevel())
                    .build();
            resume.getLanguageSkills().add(languageSkill);
        });
    }

    private void addIntroductions(Resume resume, List<IntroductionSaveRequest> introductionSaveRequests) {
        if (introductionSaveRequests == null) return;
        introductionSaveRequests.forEach(dto -> {
            Introduction introduction = Introduction.builder()
                    .content(dto.content())
                    .build();
            resume.getIntroductions().add(introduction);
        });
    }

}
