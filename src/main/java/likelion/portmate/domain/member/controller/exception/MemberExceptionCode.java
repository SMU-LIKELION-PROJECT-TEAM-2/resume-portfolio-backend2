package likelion.portmate.domain.member.controller.exception;

import likelion.portmate.global.exception.ExceptionCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
@RequiredArgsConstructor
public enum MemberExceptionCode implements ExceptionCode {
    DUPLICATE_LOGIN_ID(CONFLICT, "중복된 로그인 ID입니다."),
    DUPLICATE_EMAIL(CONFLICT, "중복된 이메일입니다."),
    MEMBER_NOT_FOUND(NOT_FOUND, "가입된 사용자가 없습니다."),
    DUPLICATE_USERNAME(CONFLICT, "중복된 이름입니다."),
    LOGIN_FAILED(UNAUTHORIZED, "아이디 또는 비밀번호가 일치하지 않습니다.");

    private final HttpStatus status;
    private final String message;

    @Override
    public String getCode() {
        return this.name();
    }

}
