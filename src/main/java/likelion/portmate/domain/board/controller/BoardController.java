package likelion.portmate.domain.board.controller;

import jakarta.validation.Valid;
import likelion.portmate.domain.board.dto.request.BoardSaveRequest;
import likelion.portmate.domain.board.dto.response.BoardInfoResponse;
import likelion.portmate.domain.board.dto.response.BoardSaveResponse;
import likelion.portmate.domain.board.entity.BoardCategory;
import likelion.portmate.domain.board.service.BoardService;
import likelion.portmate.global.annotation.MemberId;
import likelion.portmate.global.response.PageableResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/boards")
@RestController
public class BoardController implements BoardDocsController{

    private final BoardService boardService;

    @PostMapping
    public ResponseEntity<BoardSaveResponse> saveBoard(
            @MemberId Long memberId,
            @Valid @RequestBody BoardSaveRequest boardSaveRequest
    ) {
        BoardSaveResponse response = boardService.saveBoard(memberId, boardSaveRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/category")
    public ResponseEntity<PageableResponse<BoardInfoResponse>> getBoardsByCategory(
            @RequestParam("type") BoardCategory boardCategory,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "8") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        PageableResponse<BoardInfoResponse> response = boardService.getBoardCategories(boardCategory, pageable);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<PageableResponse<BoardInfoResponse>> getBoards(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        PageableResponse<BoardInfoResponse> boardResponses = boardService.getBoards(pageable);
        return new ResponseEntity<>(boardResponses, HttpStatus.OK);
    }

    @GetMapping("/{boardId}")
    public ResponseEntity<BoardInfoResponse> getBoard(
            @PathVariable Long boardId
    ) {
        BoardInfoResponse boardInfoResponse = boardService.getBoardDetail(boardId);
        return new ResponseEntity<>(boardInfoResponse, HttpStatus.OK);
    }

}
