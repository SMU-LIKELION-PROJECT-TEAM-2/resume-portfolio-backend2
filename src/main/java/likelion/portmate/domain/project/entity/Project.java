package likelion.portmate.domain.project.entity;

import jakarta.persistence.*;
import likelion.portmate.domain.resume.entity.Resume;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resume_id")
    private Resume resume;

    private String projectName;

    private String organization;

    private LocalDate startDate;

    private LocalDate endDate;

    private String description;

    @Builder
    public Project(Resume resume, String projectName, String organization, LocalDate startDate, LocalDate endDate, String description) {
        this.resume = resume;
        this.projectName = projectName;
        this.organization = organization;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
    }
}
