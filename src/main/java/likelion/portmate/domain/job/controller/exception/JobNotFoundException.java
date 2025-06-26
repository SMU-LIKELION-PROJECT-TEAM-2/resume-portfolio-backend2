package likelion.portmate.domain.job.controller.exception;

import likelion.portmate.global.exception.CustomException;

public class JobNotFoundException extends CustomException {
    public JobNotFoundException() {
        super(JobExceptionCode.JOB_NOT_FOUND);
    }
}
