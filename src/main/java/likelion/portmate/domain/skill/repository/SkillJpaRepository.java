package likelion.portmate.domain.skill.repository;

import likelion.portmate.domain.skill.entity.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkillJpaRepository extends JpaRepository<Skill, Long> {

}
