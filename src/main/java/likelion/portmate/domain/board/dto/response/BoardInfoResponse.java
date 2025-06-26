package likelion.portmate.domain.board.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record BoardInfoResponse(

        @Schema(
                description = "작성자 닉네임",
                example = "포트메이트"
        )
        String username,

        @Schema(
                description = "게시글 작성 시간",
                example = "2025-06-26T13:30:00"
        )
        LocalDateTime createdAt,

        @Schema(
                description = "게시글 제목",
                example = "포트메이트에 오신 것을 환영합니다!"
        )
        String title,

        @Schema(
                description = "커뮤니티 카테고리",
                example = "DAILY"
        )
        String boardCategory,

        @Schema(
                description = "직업 카테고리",
                example = "개발자"
        )
        String departmentCategory,

        @Schema(
                description = "게시글 내용",
                example = "이 커뮤니티는 소프트웨어 관련 정보를 공유하는 공간입니다."
        )
        String content

) {
}
