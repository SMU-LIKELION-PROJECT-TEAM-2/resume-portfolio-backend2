package likelion.portmate.domain.skill.entity;

import likelion.portmate.domain.skill.dto.response.SkillInfoResponse;

import java.util.List;

public interface SkillRepository {

    List<SkillInfoResponse> findAllByFavoriteSkillContaining(List<String> keywords);

    Skill findBySkillId(Long skillId);
}
