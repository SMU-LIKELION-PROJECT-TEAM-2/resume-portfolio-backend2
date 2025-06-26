package likelion.portmate.domain.board.service;

import likelion.portmate.domain.board.dto.request.BoardSaveRequest;
import likelion.portmate.domain.board.dto.response.BoardInfoResponse;
import likelion.portmate.domain.board.dto.response.BoardSaveResponse;
import likelion.portmate.domain.board.entity.Board;
import likelion.portmate.domain.board.entity.BoardCategory;
import likelion.portmate.domain.board.entity.BoardRepository;
import likelion.portmate.domain.board.entity.DepartmentCategory;
import likelion.portmate.domain.member.controller.exception.MemberNotFoundException;
import likelion.portmate.domain.member.entity.Member;
import likelion.portmate.domain.member.entity.MemberRepository;
import likelion.portmate.global.response.PageableResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;

    @Transactional
    public BoardSaveResponse saveBoard(Long memberId, BoardSaveRequest request) {
        Member member = memberRepository.findByMemberId(memberId)
                .orElseThrow(MemberNotFoundException::new);

        Board board = Board.builder()
                .member(member)
                .title(request.title())
                .content(request.content())
                .boardCategory(BoardCategory.valueOf(request.boardCategory()))
                .departmentCategory(DepartmentCategory.valueOf(request.departmentCategory()))
                .build();
        boardRepository.save(board);

        return BoardSaveResponse.builder()
                .boardId(board.getId())
                .build();
    }

    @Transactional(readOnly = true)
    public PageableResponse<BoardInfoResponse> getBoardCategories(BoardCategory boardCategory, Pageable pageable) {
        Page<Board> boards = boardRepository.findAllByBoardCategory(boardCategory, pageable);

        List<BoardInfoResponse> responses = boards.stream()
                .map(board -> BoardInfoResponse.builder()
                        .username(board.getMember().getUsername())
                        .createdAt(board.getCreatedAt())
                        .title(board.getTitle())
                        .boardCategory(String.valueOf(board.getBoardCategory()))
                        .departmentCategory(String.valueOf(board.getDepartmentCategory()))
                        .content(board.getContent())
                        .build()
                )
                .toList();

        return PageableResponse.of(pageable, responses);
    }

    @Transactional(readOnly = true)
    public PageableResponse<BoardInfoResponse> getBoards(Pageable pageable) {
        Page<Board> boards = boardRepository.findAll(pageable);

        List<BoardInfoResponse> boardResponses = boards.stream()
                .map(board -> BoardInfoResponse.builder()
                        .username(board.getMember().getUsername())
                        .createdAt(board.getCreatedAt())
                        .title(board.getTitle())
                        .boardCategory(board.getBoardCategory().name())
                        .departmentCategory(board.getDepartmentCategory().name())
                        .content(board.getContent())
                        .build()
                )
                .toList();

        return PageableResponse.of(pageable, boardResponses);
    }

    @Transactional(readOnly = true)
    public BoardInfoResponse getBoardDetail(Long boardId) {
        Board board = boardRepository.findByBoardId(boardId);

        return BoardInfoResponse.builder()
                .username(board.getMember().getUsername())
                .createdAt(board.getCreatedAt())
                .title(board.getTitle())
                .content(board.getContent())
                .boardCategory(board.getBoardCategory().name())
                .departmentCategory(board.getDepartmentCategory().name())
                .build();
    }

}
