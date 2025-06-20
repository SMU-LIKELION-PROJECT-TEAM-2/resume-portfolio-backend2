package likelion.portmate.global.auth.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

public record LoginResultRequest(


        @Schema(
                description = "로그인에 사용할 아이디",
                example = "portmate123",
                requiredMode = REQUIRED
        )
        @NotNull(message = "로그인 아이디를 입력해 주세요.")
        String loginId,

        @Schema(
                description = "비밀번호",
                example = "securePassword123!",
                requiredMode = REQUIRED
        )
        @NotNull(message = "비밀번호를 입력해 주세요.")
        String password

) {
}
