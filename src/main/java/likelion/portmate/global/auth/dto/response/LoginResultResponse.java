package likelion.portmate.global.auth.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

@Schema(description = "로그인 결과 응답 DTO")
public record LoginResultResponse(

        @Schema(
                description = "발급된 Access Token",
                example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
                requiredMode = REQUIRED
        )
        String accessToken,

        @Schema(
                description = "발급된 Refresh Token",
                example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
                requiredMode = REQUIRED
        )
        String refreshToken

) {
}
