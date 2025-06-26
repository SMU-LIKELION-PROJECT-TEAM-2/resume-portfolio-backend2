package likelion.portmate.domain.education.entity;

import jakarta.persistence.*;
import likelion.portmate.domain.resume.entity.Resume;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.YearMonth;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Education {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resume_id")
    private Resume resume;

    @Enumerated(EnumType.STRING)
    private EducationType educationType;

    private String organization;

    private String majorName;

    private String majorCategory;

    private LocalDate startDate;

    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    private EnrollmentStatus enrollmentStatus;

    @Builder
    public Education(
            Resume resume,
            EducationType educationType,
            String organization,
            String majorName,
            String majorCategory,
            LocalDate startDate,
            LocalDate endDate,
            EnrollmentStatus enrollmentStatus
    ) {
        this.resume = resume;
        this.educationType = educationType;
        this.organization = organization;
        this.majorName = majorName;
        this.majorCategory = majorCategory;
        this.startDate = startDate;
        this.endDate = endDate;
        this.enrollmentStatus = enrollmentStatus;
    }
}
