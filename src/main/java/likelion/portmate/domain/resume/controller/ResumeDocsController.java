package likelion.portmate.domain.resume.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import likelion.portmate.domain.resume.dto.request.ResumeSaveRequest;
import org.springframework.http.ResponseEntity;

@Tag(name = "Resume", description = "이력서 관련 API")
public interface ResumeDocsController {

    @Operation(summary = "이력서 저장", description = "회원이 작성한 이력서를 저장합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "이력서 저장 성공"),
            @ApiResponse(responseCode = "400", description = "요청 데이터 오류", content = @Content),
            @ApiResponse(responseCode = "404", description = "회원을 찾을 수 없음", content = @Content)
    })
    ResponseEntity<Void> save(
            @Parameter(description = "작성자 ID", example = "1") Long memberId,
            @RequestBody(description = "이력서 저장 요청", required = true,
                    content = @Content(schema = @Schema(implementation = ResumeSaveRequest.class)))
            ResumeSaveRequest request
    );
}
