package likelion.portmate.domain.resume.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import likelion.portmate.domain.career.dto.request.CareerSaveRequest;
import likelion.portmate.domain.certification.dto.request.CertificationSaveRequest;
import likelion.portmate.domain.education.dto.request.EducationSaveRequest;
import likelion.portmate.domain.externalActivity.dto.request.ExternalActivitySaveRequest;
import likelion.portmate.domain.introduction.dto.request.IntroductionSaveRequest;
import likelion.portmate.domain.languageskill.dto.request.LanguageSkillSaveRequest;
import likelion.portmate.domain.portfolio.dto.request.PortfolioSaveRequest;
import likelion.portmate.domain.project.dto.requeset.ProjectSaveRequest;
import likelion.portmate.domain.skill.dto.request.SkillSaveRequest;

import java.util.List;

public record ResumeSaveRequest(

        @Email
        @NotNull
        String email,

        @NotNull
        String phoneNumber,

        List<SkillSaveRequest> skills,

        List<CareerSaveRequest> careers,

        List<ProjectSaveRequest> projects,

        List<PortfolioSaveRequest> portfolios,

        List<EducationSaveRequest> educations,

        List<ExternalActivitySaveRequest> externalActivities,

        List<CertificationSaveRequest> certifications,

        List<LanguageSkillSaveRequest> languageSkills,

        List<IntroductionSaveRequest> introductions

) {
}
