package likelion.portmate.domain.interestfield.controller.exception;

import likelion.portmate.global.exception.CustomException;

public class InterestFieldNotFoundException extends CustomException {
    public InterestFieldNotFoundException() {
        super(InterestFieldExceptionCode.INTEREST_FIELD_NOT_FOUND);
    }
}
