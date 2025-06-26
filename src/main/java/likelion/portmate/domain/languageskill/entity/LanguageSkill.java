package likelion.portmate.domain.languageskill.entity;

import jakarta.persistence.*;
import likelion.portmate.domain.resume.entity.Resume;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class LanguageSkill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resume_id")
    private Resume resume;

    @Enumerated(EnumType.STRING)
    private LanguageName languageName;

    @Enumerated(EnumType.STRING)
    private ProficiencyLevel proficiencyLevel;

    @Builder
    public LanguageSkill(Resume resume, LanguageName languageName, ProficiencyLevel proficiencyLevel) {
        this.resume = resume;
        this.languageName = languageName;
        this.proficiencyLevel = proficiencyLevel;
    }

}
