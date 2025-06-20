package likelion.portmate.global.auth.controller.exception;

import likelion.portmate.global.exception.CustomException;

public class AuthenticationRequiredException extends CustomException {
    public AuthenticationRequiredException() {
        super(AuthExceptionCode.AUTHENTICATION_REQUIRED);
    }
}
