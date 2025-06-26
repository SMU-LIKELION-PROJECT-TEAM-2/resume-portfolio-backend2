package likelion.portmate.domain.externalActivity.entity;

import jakarta.persistence.*;
import likelion.portmate.domain.resume.entity.Resume;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class ExternalActivity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resume_id")
    private Resume resume;

    private String activityName;

    private String affiliation;

    private String activityOrganization;

    private String description;

    @Builder
    public ExternalActivity(Resume resume, String activityName, String affiliation, String activityOrganization, String description) {
        this.resume = resume;
        this.activityName = activityName;
        this.affiliation = affiliation;
        this.activityOrganization = activityOrganization;
        this.description = description;
    }
}
