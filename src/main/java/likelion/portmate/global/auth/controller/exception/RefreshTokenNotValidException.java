package likelion.portmate.global.auth.controller.exception;

import likelion.portmate.global.exception.CustomException;

public class RefreshTokenNotValidException extends CustomException {
    public RefreshTokenNotValidException() {
        super(AuthExceptionCode.REFRESH_TOKEN_NOT_VALID);
    }
}
