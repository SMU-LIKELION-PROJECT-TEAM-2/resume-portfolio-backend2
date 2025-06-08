package likelion.portmate.domain.member.controller.exception;

import likelion.portmate.global.exception.CustomException;

public class DuplicateLoginIdException extends CustomException {
    public DuplicateLoginIdException() {
        super(MemberExceptionCode.DUPLICATE_LOGIN_ID);
    }
}
