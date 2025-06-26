package likelion.portmate.domain.skill.controller.exception;

import likelion.portmate.global.exception.ExceptionCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum SkillExceptionCode implements ExceptionCode {
    SKILL_NOT_FOUND(HttpStatus.NOT_FOUND, "기술을 찾을 수 없습니다."),
    ;

    private final HttpStatus status;
    private final String message;

    @Override
    public String getCode() {
        return this.name();
    }
}
