package likelion.portmate.domain.board.repository;

import likelion.portmate.domain.board.entity.Board;
import likelion.portmate.domain.board.entity.BoardCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardJpaRepository extends JpaRepository<Board, Long> {

    Page<Board> findAllByBoardCategory(BoardCategory boardCategory, Pageable pageable);

}
