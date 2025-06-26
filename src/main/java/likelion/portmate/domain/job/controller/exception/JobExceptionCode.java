package likelion.portmate.domain.job.controller.exception;

import likelion.portmate.global.exception.ExceptionCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Getter
@RequiredArgsConstructor
public enum JobExceptionCode implements ExceptionCode {
    JOB_NOT_FOUND(NOT_FOUND, "해당 직업을 찾을 수 없습니다."),
    ;

    private final HttpStatus status;
    private final String message;

    @Override
    public String getCode() {
        return this.name();
    }

}
