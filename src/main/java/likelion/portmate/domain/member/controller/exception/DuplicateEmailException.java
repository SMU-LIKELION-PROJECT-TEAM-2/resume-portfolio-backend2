package likelion.portmate.domain.member.controller.exception;

import likelion.portmate.global.exception.CustomException;

public class DuplicateEmailException extends CustomException {
    public DuplicateEmailException() {
        super(MemberExceptionCode.DUPLICATE_EMAIL);
    }
}
