package likelion.portmate.domain.member.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import likelion.portmate.domain.member.dto.LoginRequestDto;
import likelion.portmate.domain.member.dto.SignupRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "Auth", description = "회원가입 · 로그인 API")
public interface AuthDocsController {

    @Operation(summary = "회원가입", description = "새로운 사용자를 등록합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "회원가입 성공"),
            @ApiResponse(responseCode = "400", description = "유효성 검사 실패")
    })
    ResponseEntity<?> signup(@RequestBody SignupRequestDto dto);

    @Operation(summary = "로그인", description = "아이디/비밀번호로 로그인하고 토큰을 발급받습니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "로그인 성공"),
            @ApiResponse(responseCode = "401", description = "인증 실패")
    })
    ResponseEntity<?> login(@RequestBody LoginRequestDto dto);

    @Operation(summary = "아이디 중복 검사", description = "loginId 가 이미 존재하는지 확인합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "사용 가능"),
            @ApiResponse(responseCode = "409", description = "이미 존재")
    })
    ResponseEntity<?> signupCheckDuplicateId(
            @Parameter(description = "중복 확인할 로그인 ID", example = "portmate123")
            @RequestParam String loginId
    );
}
