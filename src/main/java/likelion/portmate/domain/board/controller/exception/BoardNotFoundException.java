package likelion.portmate.domain.board.controller.exception;

import likelion.portmate.global.exception.CustomException;

public class BoardNotFoundException extends CustomException {
    public BoardNotFoundException() {
        super(BoardExceptionCode.BOARD_NOT_FOUND);
    }
}
