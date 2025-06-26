package likelion.portmate.domain.skill.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String favoriteSkillEng;

    private String favoriteSkillKor;

    @Builder
    public Skill(String favoriteSkillEng, String favoriteSkillKor) {
        this.favoriteSkillEng = favoriteSkillEng;
        this.favoriteSkillKor = favoriteSkillKor;
    }

}
