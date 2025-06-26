package likelion.portmate.domain.skill.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.StringPath;
import com.querydsl.jpa.impl.JPAQueryFactory;
import likelion.portmate.domain.skill.dto.response.SkillInfoResponse;
import likelion.portmate.domain.skill.entity.QSkill;
import likelion.portmate.domain.skill.entity.Skill;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Repository
public class SkillQueryDslRepository {

    private final JPAQueryFactory queryFactory;

    public List<SkillInfoResponse> findAllByFavoriteSkillContaining(List<String> keywords) {
        QSkill skill = QSkill.skill;

        BooleanExpression whereClause = buildKeywordCondition(keywords, skill.favoriteSkillEng, skill.favoriteSkillKor);

        List<Skill> skills = queryFactory.selectFrom(skill)
                .where(whereClause)
                .orderBy(skill.id.desc())
                .fetch();

        return skills.stream()
                .map(this::convertToSkillInfoResponse)
                .collect(Collectors.toList());
    }

    private BooleanExpression buildKeywordCondition(List<String> keywords, StringPath favoriteSkillEng, StringPath favoriteSkillKor) {
        if (keywords == null || keywords.isEmpty()) {
            return null;
        }

        BooleanExpression condition = null;

        for (String keyword : keywords) {
            BooleanExpression engContains = favoriteSkillEng.containsIgnoreCase(keyword);
            BooleanExpression korContains = favoriteSkillKor.containsIgnoreCase(keyword);
            BooleanExpression keywordCondition = engContains.or(korContains);

            if (condition == null) {
                condition = keywordCondition;
            } else {
                condition = condition.or(keywordCondition);
            }
        }

        return condition;
    }

    private SkillInfoResponse convertToSkillInfoResponse(Skill skill) {
        return new SkillInfoResponse(
                skill.getFavoriteSkillEng(),
                skill.getFavoriteSkillKor()
        );
    }
}
