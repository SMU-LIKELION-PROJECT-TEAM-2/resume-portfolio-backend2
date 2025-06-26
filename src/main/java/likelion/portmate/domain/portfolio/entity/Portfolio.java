package likelion.portmate.domain.portfolio.entity;

import jakarta.persistence.*;
import likelion.portmate.domain.resume.entity.Resume;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Portfolio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resume_id")
    private Resume resume;

    private String portfolioUrl;

    @Builder
    public Portfolio(Resume resume, String portfolioUrl) {
        this.resume = resume;
        this.portfolioUrl = portfolioUrl;
    }

}
