package likelion.portmate.domain.skill.controller;

import likelion.portmate.domain.skill.dto.response.SkillInfoResponse;
import likelion.portmate.domain.skill.service.SkillService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/skills")
@RestController
public class SkillController implements SkillDocsController {

    private final SkillService skillService;

    @GetMapping("/search")
    public ResponseEntity<List<SkillInfoResponse>> searchSkills(
            @RequestParam List<String> keywords
    ) {
        List<SkillInfoResponse> responses = skillService.findAllByFavoriteSkillContaining(keywords);
        return ResponseEntity.ok(responses);
    }

}
