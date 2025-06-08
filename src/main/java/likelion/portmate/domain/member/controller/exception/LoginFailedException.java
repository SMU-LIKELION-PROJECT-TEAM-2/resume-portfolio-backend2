package likelion.portmate.domain.member.controller.exception;

import likelion.portmate.global.exception.CustomException;

public class LoginFailedException extends CustomException {
    public LoginFailedException() {
        super(MemberExceptionCode.LOGIN_FAILED);
    }
}
