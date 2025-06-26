package likelion.portmate.domain.board.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import likelion.portmate.domain.board.dto.request.BoardSaveRequest;
import likelion.portmate.domain.board.dto.response.BoardInfoResponse;
import likelion.portmate.domain.board.dto.response.BoardSaveResponse;
import likelion.portmate.domain.board.entity.BoardCategory;
import likelion.portmate.global.response.PageableResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

@Tag(name = "Board", description = "게시글 등록 · 카테고리별 조회 API")
public interface BoardDocsController {

    @Operation(summary = "게시글 등록", description = "새로운 게시글을 저장합니다.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "게시글 저장 성공",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = BoardSaveResponse.class)
                    )
            )
    })
    ResponseEntity<BoardSaveResponse> saveBoard(
            @Parameter(description = "작성자 ID", example = "1") Long memberId,
            @Valid @RequestBody BoardSaveRequest boardSaveRequest
    );

    @Operation(summary = "카테고리별 게시글 조회", description = "카테고리와 페이징 정보를 이용해 게시글 목록을 조회합니다.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "조회 성공",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = BoardInfoResponse.class)
                    )
            )
    })
    ResponseEntity<PageableResponse<BoardInfoResponse>> getBoardsByCategory(
            @Parameter(description = "게시판 카테고리 (예: 일상, 피드백 등)", example = "DAILY")
            @RequestParam("type") BoardCategory boardCategory,

            @Parameter(description = "페이지 번호 (0부터 시작)", example = "0")
            @RequestParam(defaultValue = "0") int page,

            @Parameter(description = "페이지당 게시글 수", example = "8")
            @RequestParam(defaultValue = "8") int size
    );

    @Operation(summary = "전체 게시글 조회", description = "전체 게시글을 조회합니다.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "조회 성공",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = BoardInfoResponse.class)
                    )
            )
    })
    ResponseEntity<PageableResponse<BoardInfoResponse>> getBoards(
            @Parameter(description = "페이지 번호 (0부터 시작)", example = "0")
            @RequestParam(defaultValue = "0") int page,

            @Parameter(description = "페이지당 게시글 수", example = "8")
            @RequestParam(defaultValue = "8") int size
    );
}
