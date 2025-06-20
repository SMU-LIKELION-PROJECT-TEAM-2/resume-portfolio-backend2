package likelion.portmate.global.auth.controller.exception;

import likelion.portmate.global.exception.CustomException;

public class AlreadyRegisteredMemberException extends CustomException {
    public AlreadyRegisteredMemberException() {
        super(AuthExceptionCode.ALREADY_REGISTERED_MEMBER);
    }
}
