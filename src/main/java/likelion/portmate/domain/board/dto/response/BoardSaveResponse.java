package likelion.portmate.domain.board.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

@Builder
public record BoardSaveResponse(

        @Schema(
                description = "저장된 게시글의 ID",
                example = "1",
                requiredMode = REQUIRED
        )
        Long boardId

) {
}
