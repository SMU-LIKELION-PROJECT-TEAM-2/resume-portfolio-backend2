package likelion.portmate.domain.skill.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record SkillInfoResponse(

        @Schema(
                description = "기술 영어명",
                example = "JAVA"
        )
        String favoriteSkillEng,

        @Schema(
                description = "기술 한글명",
                example = "자바"
        )
        String favoriteSkillKor

) {
}
