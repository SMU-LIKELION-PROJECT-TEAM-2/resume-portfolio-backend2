package likelion.portmate.domain.skill.controller.exception;

import likelion.portmate.global.exception.CustomException;

public class SkillNotFoundException extends CustomException {
    public SkillNotFoundException() {
        super(SkillExceptionCode.SKILL_NOT_FOUND);
    }
}
