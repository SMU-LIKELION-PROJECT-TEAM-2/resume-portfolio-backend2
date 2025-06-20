package likelion.portmate.domain.kakao.controller;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "kakao-login-controller", description = "카카오 OAuth 로그인")
public interface KakaoLoginDocsController {

    @Operation(
            summary     = "카카오 OAuth 콜백",
            description = """
            프론트엔드(또는 Kakao)가 redirect 해준 <code>?code=인증코드</code>를 받아<br>
            1) 카카오 AccessToken ↩ 교환<br>
            2) 사용자 정보 조회 및 최초 로그인 시 자동 회원가입<br>
            3) 우리 서비스용 JWT(AT·RT) 발급 후 내려줍니다.
            """,
            parameters = {
                    @Parameter(
                            name        = "code",
                            description = "카카오에서 전달된 인가 코드",
                            required    = true,
                            example     = "W7e1sULpA_OdjpSvPSPqIG"
                    )
            },
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description  = "로그인/회원가입 + JWT 발급 성공",
                            content      = @Content(
                                    schema = @Schema(implementation = KakaoLoginDto.class)
                            )
                    ),
                    @ApiResponse(responseCode = "400", description = "잘못된 파라미터"),
                    @ApiResponse(responseCode = "500", description = "서버 오류")
            }
    )
    ResponseEntity<KakaoLoginDto> callback(@RequestParam("code") String code);
}
