package likelion.portmate.domain.career.entity;

import jakarta.persistence.*;
import likelion.portmate.domain.resume.entity.Resume;
import likelion.portmate.global.domain.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.YearMonth;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Career extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resume_id")
    private Resume resume;

    private String companyName;

    private LocalDate startDate;

    private LocalDate endDate;

    private String position;

    private String departmentName;

    @Enumerated(EnumType.STRING)
    private WorkType workType;

    private String responsibilities;

    @Builder
    public Career(
            Resume resume,
            String companyName,
            LocalDate startDate,
            LocalDate endDate,
            String position,
            String departmentName,
            WorkType workType,
            String responsibilities
    ) {
        this.resume = resume;
        this.companyName = companyName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.position = position;
        this.departmentName = departmentName;
        this.workType = workType;
        this.responsibilities = responsibilities;
    }

}
