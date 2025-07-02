package likelion.portmate.domain.profilecareer.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record ProfileCareerUpdateRequest(
        @Schema(description = "커리어 ID (신규 항목은 null)", nullable = true, example = "null")
        Long id,

        @NotBlank
        @Schema(description = "커리어 제목", example = "인턴")
        String title,

        @Schema(description = "상세 설명", example = "백엔드 개발 보조")
        String description,

        @NotNull LocalDate startDate,
        LocalDate endDate
) {}