package likelion.portmate.domain.job.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import likelion.portmate.domain.job.dto.response.JobInfoResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Tag(name = "Job", description = "직무 조회 및 검색 API")
public interface JobDocsController {

    @Operation(summary = "카테고리별 직무 조회", description = "직업 카테고리를 기반으로 관련 직무를 조회합니다.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "직무 조회 성공",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = JobInfoResponse.class)
                    )
            )
    })
    ResponseEntity<List<JobInfoResponse>> getJobs(
            @Parameter(description = "직업 카테고리", example = "DEVELOPMENT 선택 -> 소프트웨어 엔지니어, 프론트엔드 개발자, 백엔드 개발자 등등 보여짐")
            @RequestParam("type") String departmentCategory
    );

    @Operation(summary = "직무 검색", description = "키워드 리스트를 기반으로 직무를 검색합니다.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "검색 결과 반환",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = JobInfoResponse.class)
                    )
            )
    })
    ResponseEntity<List<JobInfoResponse>> searchJobs(
            @Parameter(description = "검색 키워드 리스트", example = """
                    ["DEVELOPMENT,
                        DESIGN,
                        MARKETING,
                        MANAGEMENT_HR,
                        SALES"]""")
            @RequestParam List<String> keywords
    );
}
