package likelion.portmate.domain.skill.repository;

import likelion.portmate.domain.skill.controller.exception.SkillNotFoundException;
import likelion.portmate.domain.skill.dto.response.SkillInfoResponse;
import likelion.portmate.domain.skill.entity.Skill;
import likelion.portmate.domain.skill.entity.SkillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class SkillRepositoryImpl implements SkillRepository {

    private final SkillJpaRepository skillJpaRepository;
    private final SkillQueryDslRepository skillQueryDslRepository;

    @Override
    public List<SkillInfoResponse> findAllByFavoriteSkillContaining(List<String> keywords) {
        return skillQueryDslRepository.findAllByFavoriteSkillContaining(keywords);
    }

    @Override
    public Skill findBySkillId(Long skillId) {
        return skillJpaRepository.findById(skillId)
                .orElseThrow(SkillNotFoundException::new);
    }

}
