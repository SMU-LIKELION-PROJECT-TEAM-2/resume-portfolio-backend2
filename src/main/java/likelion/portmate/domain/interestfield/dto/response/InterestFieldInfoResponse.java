package likelion.portmate.domain.interestfield.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record InterestFieldInfoResponse(

        @Schema(
                description = "관심 분야 영어명",
                example = "BACKEND"
        )
        String interestFieldEng,

        @Schema(
                description = "관심 분야 한글명",
                example = "백엔드"
        )
        String interestFieldKor

) {
}
