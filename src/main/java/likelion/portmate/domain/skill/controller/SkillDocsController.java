package likelion.portmate.domain.skill.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import likelion.portmate.domain.skill.dto.response.SkillInfoResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Tag(name = "Skill", description = "기술 스택 검색 API")
public interface SkillDocsController {

    @Operation(summary = "기술 검색", description = "키워드 리스트를 기반으로 기술 스택을 검색합니다.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "기술 검색 성공",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = SkillInfoResponse.class)
                    )
            )
    })
    ResponseEntity<List<SkillInfoResponse>> searchSkills(
            @Parameter(description = "검색 키워드 리스트", example = "[\"Java\", \"Spring\"]")
            @RequestParam List<String> keywords
    );
}
