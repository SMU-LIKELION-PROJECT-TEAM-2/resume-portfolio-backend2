package likelion.portmate.domain.board.repository;

import likelion.portmate.domain.board.controller.exception.BoardNotFoundException;
import likelion.portmate.domain.board.entity.Board;
import likelion.portmate.domain.board.entity.BoardCategory;
import likelion.portmate.domain.board.entity.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class BoardRepositoryImpl implements BoardRepository {

    private final BoardJpaRepository boardJpaRepository;

    @Override
    public void save(Board board) {
        boardJpaRepository.save(board);
    }

    @Override
    public Page<Board> findAllByBoardCategory(BoardCategory boardCategory, Pageable pageable) {
        return boardJpaRepository.findAllByBoardCategory(boardCategory, pageable);
    }

    @Override
    public Page<Board> findAll(Pageable pageable) {
        return boardJpaRepository.findAll(pageable);
    }

    @Override
    public Board findByBoardId(Long boardId) {
        return boardJpaRepository.findById(boardId)
                .orElseThrow(BoardNotFoundException::new);
    }



}
