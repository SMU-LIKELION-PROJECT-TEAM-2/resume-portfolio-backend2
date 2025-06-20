package likelion.portmate.global.auth.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import likelion.portmate.global.auth.dto.request.LoginResultRequest;
import likelion.portmate.global.auth.dto.response.LoginResultResponse;
import org.springframework.http.ResponseEntity;

@Tag(name = "Login", description = "일반 로그인 · 소셜 로그인 API")
public interface AuthDocsController {

    @Operation(summary = "일반 로그인", description = "loginId와 password로 로그인하고 JWT 토큰을 반환합니다.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "일반 로그인 성공",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = LoginResultRequest.class)
                    )
            )
    })
    ResponseEntity<LoginResultResponse> login(@Valid @RequestBody LoginResultRequest request);

    @Operation(summary = "카카오 로그인 리디렉션", description = "카카오 로그인 페이지로 리디렉션합니다.")
    @ApiResponse(
            responseCode = "302",
            description = "카카오 로그인 페이지로 리디렉션"
    )
    String login();
}
