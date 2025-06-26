package likelion.portmate.domain.interestfield.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import likelion.portmate.domain.interestfield.dto.response.InterestFieldInfoResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Tag(name = "InterestField", description = "관심 분야 검색 API")
public interface InterestFieldDocsController {

    @Operation(summary = "관심 분야 키워드 검색", description = "키워드를 포함한 관심 분야 목록을 조회합니다.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "관심 분야 검색 성공",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = InterestFieldInfoResponse.class)
                    )
            )
    })
    ResponseEntity<List<InterestFieldInfoResponse>> findAllByInterestFieldContaining(
            @Parameter(description = "검색 키워드 리스트", example = "[\"백엔드\", \"프론트\"]")
            @RequestParam List<String> keywords
    );
}
