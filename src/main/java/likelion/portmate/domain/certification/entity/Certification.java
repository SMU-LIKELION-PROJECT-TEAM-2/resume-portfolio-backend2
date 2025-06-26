package likelion.portmate.domain.certification.entity;

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
public class Certification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resume_id")
    private Resume resume;

    private String name;

    private String scoreOrGrade;

    private String issuingOrganization;

    private LocalDate acquisitionDate;

    @Builder
    public Certification(Resume resume, String name, String scoreOrGrade, String issuingOrganization, LocalDate acquisitionDate) {
        this.resume = resume;
        this.name = name;
        this.scoreOrGrade = scoreOrGrade;
        this.issuingOrganization = issuingOrganization;
        this.acquisitionDate = acquisitionDate;
    }
}
