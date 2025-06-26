package likelion.portmate.domain.board.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

@Schema(description = "게시글 저장 요청 DTO")
public record BoardSaveRequest(

        @Schema(
                description = "게시글 제목",
                example = "포트메이트에 오신 것을 환영합니다!",
                requiredMode = REQUIRED
        )
        @NotNull(message = "제목을 입력해 주세요.")
        String title,

        @Schema(
                description = "게시글 내용",
                example = "안녕하세요. 포트메이트는 커리어와 경험을 공유하는 커뮤니티입니다.",
                requiredMode = REQUIRED
        )
        @NotNull(message = "내용을 입력해 주세요.")
        String content,

        @Schema(
                description = "커뮤니티 카테고리",
                example = "DAILY",
                requiredMode = REQUIRED
        )
        @NotNull(message = "커뮤니티 카테고리를 선택해 주세요.")
        String boardCategory,

        @Schema(
                description = "직업 카테고리",
                example = "개발자",
                requiredMode = REQUIRED
        )
        @NotNull(message = "직업 카테고리를 선택해 주세요.")
        String departmentCategory

) {
}
