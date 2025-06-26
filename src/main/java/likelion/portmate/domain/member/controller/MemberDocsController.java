package likelion.portmate.domain.member.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import likelion.portmate.domain.member.dto.request.MemberSaveRequest;
import likelion.portmate.domain.member.dto.request.MemberSelectCareerProfileSaveRequest;
import likelion.portmate.domain.member.dto.response.MemberSaveResponse;
import likelion.portmate.global.annotation.MemberId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "Member", description = "회원가입 · 아이디 중복 검사 · 커리어 프로필 선택 API")
public interface MemberDocsController {

    @Operation(summary = "회원가입", description = "새로운 사용자를 등록합니다.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "회원가입 성공",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = MemberSaveResponse.class)
                    )
            )
    })
    ResponseEntity<MemberSaveResponse> signUp(
            @RequestBody(description = "회원가입 요청 DTO", required = true)
            MemberSaveRequest request
    );

    @Operation(summary = "아이디 중복 검사", description = "loginId 가 이미 존재하는지 확인합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "사용 가능"),
            @ApiResponse(responseCode = "409", description = "이미 존재")
    })
    ResponseEntity<?> signupCheckDuplicateId(
            @Parameter(description = "중복 확인할 로그인 ID", example = "portmate123")
            @RequestParam String loginId
    );

    @Operation(summary = "커리어 프로필 선택", description = "회원이 직군, 직무, 관심 분야, 기술 스택을 선택합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "선택 완료")
    })
    ResponseEntity<Void> select(
            @MemberId
            Long memberId,

            @RequestBody(description = "커리어 프로필 선택 요청", required = true)
            MemberSelectCareerProfileSaveRequest request
    );
}
