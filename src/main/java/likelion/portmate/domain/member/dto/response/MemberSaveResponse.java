package likelion.portmate.domain.member.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

@Builder
public record MemberSaveResponse(

        @Schema(
                description = "저장된 회원의 ID",
                example = "1",
                requiredMode = REQUIRED
        )
        Long memberId

) {
}
