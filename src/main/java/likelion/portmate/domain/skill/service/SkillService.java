package likelion.portmate.domain.skill.service;

import likelion.portmate.domain.skill.dto.response.SkillInfoResponse;
import likelion.portmate.domain.skill.entity.SkillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SkillService {

    private final SkillRepository skillRepository;

    @Transactional(readOnly = true)
    public List<SkillInfoResponse> findAllByFavoriteSkillContaining(List<String> keywords) {
        return skillRepository.findAllByFavoriteSkillContaining(keywords);
    }

}
