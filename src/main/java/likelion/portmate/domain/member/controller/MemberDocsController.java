package likelion.portmate.domain.member.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import likelion.portmate.domain.member.dto.request.MemberBannerUpdateRequest;
import likelion.portmate.domain.member.dto.request.MemberProfileUpdateRequest;
import likelion.portmate.domain.member.dto.request.MemberSaveRequest;
import likelion.portmate.domain.member.dto.request.MemberSelectCareerProfileSaveRequest;
import likelion.portmate.domain.member.dto.response.MemberProfileViewResponse;
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

    @Operation(summary = "배너 이미지 변경", description = "배너 이미지 URL만 교체합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "수정 완료")
    })
    ResponseEntity<Void> updateBanner(
            @MemberId Long memberId,
            @RequestBody(description = "배너 이미지 수정 DTO", required = true)
            MemberBannerUpdateRequest request
    );

    @Operation(summary = "프로필 조회", description = "회원의 이름, 배너, 자기소개, 직무, 기술 스택, 커리어 목록을 조회합니다.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "조회 성공",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = MemberProfileViewResponse.class)
                    )
            ),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 사용자")
    })
    ResponseEntity<MemberProfileViewResponse> getProfile(
            @Parameter(hidden = true) @MemberId Long memberId
    );

    @Operation(summary = "내 프로필 수정", description = """
        회원의 이름, 소개, 주소, 커리어 이력을 수정합니다.
        - 커리어 항목은 전체 수정 방식입니다.
        - `profileCareers`의 각 요소에 `id`가 있으면 해당 항목을 수정하고,
          `id`가 null이면 새로 추가됩니다.
        - 기존에 있던 항목 중 전달되지 않은 항목은 삭제됩니다.
    """)
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "수정 완료"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청")
    })
    ResponseEntity<Void> updateProfile(
            @MemberId Long memberId,
            @RequestBody(description = "회원 프로필 수정 요청", required = true)
            MemberProfileUpdateRequest request
    );


}
