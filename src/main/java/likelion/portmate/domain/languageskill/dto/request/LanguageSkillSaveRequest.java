package likelion.portmate.domain.languageskill.dto.request;

import likelion.portmate.domain.languageskill.entity.LanguageName;
import likelion.portmate.domain.languageskill.entity.ProficiencyLevel;

public record LanguageSkillSaveRequest(

        LanguageName languageName,

        ProficiencyLevel proficiencyLevel

) {
}
