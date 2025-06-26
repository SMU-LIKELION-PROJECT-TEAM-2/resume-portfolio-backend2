package likelion.portmate.domain.job.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record JobInfoResponse(

        @Schema(
                description = "직무 이름",
                example = "백엔드 개발자"
        )
        String jobName

) {
}
