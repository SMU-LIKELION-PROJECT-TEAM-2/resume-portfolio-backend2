package likelion.portmate.domain.board.entity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardRepository {

    void save(Board board);

    Page<Board> findAllByBoardCategory(BoardCategory boardCategory, Pageable pageable);

    Page<Board> findAll(Pageable pageable);

    Board findByBoardId(Long boardId);

}
