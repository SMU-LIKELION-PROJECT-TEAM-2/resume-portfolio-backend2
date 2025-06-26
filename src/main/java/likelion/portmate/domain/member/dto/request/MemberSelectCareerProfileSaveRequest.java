package likelion.portmate.domain.member.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public record MemberSelectCareerProfileSaveRequest(

        @Schema(
                description = "직군 (예: DEVELOPMENT,\n" +
                        "    DESIGN,\n" +
                        "    MARKETING,\n" +
                        "    MANAGEMENT_HR,\n" +
                        "    SALES 등)",
                example = "DEVELOPMENT"
        )
        @NotNull(message = "직군을 선택해 주세요.")
        String departmentCategory,

        @Schema(
                description = "선택한 직무 ID",
                example = "1"
        )
        @NotNull(message = "직무를 선택해 주세요.")
        Long jobId,

        @Schema(
                description = "선택한 관심 분야 ID",
                example = "2"
        )
        @NotNull(message = "관심 분야를 선택해 주세요.")
        Long interestFieldId,

        @Schema(
                description = "선택한 기술 스택 ID",
                example = "3"
        )
        @NotNull(message = "기술을 선택해 주세요.")
        Long skillId

) {
}
