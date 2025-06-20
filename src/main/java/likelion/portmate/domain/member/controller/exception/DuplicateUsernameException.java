package likelion.portmate.domain.member.controller.exception;

import likelion.portmate.global.exception.CustomException;

public class DuplicateUsernameException extends CustomException {
    public DuplicateUsernameException() {
        super(MemberExceptionCode.DUPLICATE_USERNAME);
    }
}
