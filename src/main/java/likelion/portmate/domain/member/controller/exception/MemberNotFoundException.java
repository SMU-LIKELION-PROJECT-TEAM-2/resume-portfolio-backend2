package likelion.portmate.domain.member.controller.exception;

import likelion.portmate.global.exception.CustomException;

public class MemberNotFoundException extends CustomException {
    public MemberNotFoundException() {
        super(MemberExceptionCode.MEMBER_NOT_FOUND);
    }
}
