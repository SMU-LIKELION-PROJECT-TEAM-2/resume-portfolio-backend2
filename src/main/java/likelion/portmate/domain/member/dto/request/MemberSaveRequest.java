package likelion.portmate.domain.member.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

@Schema(description = "회원가입 요청 DTO")
public record MemberSaveRequest(

        @Schema(
                description = "로그인에 사용할 아이디",
                example = "portmate123",
                requiredMode = REQUIRED
        )
        @NotNull(message = "아이디를 입력해 주세요.")
        String loginId,

        @Schema(
                description = "사용자 이메일",
                example = "user@example.com",
                requiredMode = REQUIRED
        )
        @NotNull(message = "이메일을 입력해 주세요.")
        String email,

        @Schema(
                description = "비밀번호",
                example = "securePassword123!",
                requiredMode = REQUIRED
        )
        @NotNull(message = "비밀번호를 입력해 주세요.")
        String password,

        @Schema(
                description = "사용자 닉네임",
                example = "포트메이트",
                requiredMode = REQUIRED
        )
        @NotNull(message = "닉네임을 입력해 주세요.")
        String username

) {
}
