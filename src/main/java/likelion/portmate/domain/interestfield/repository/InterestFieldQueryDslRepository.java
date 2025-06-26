package likelion.portmate.domain.interestfield.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.StringPath;
import com.querydsl.jpa.impl.JPAQueryFactory;
import likelion.portmate.domain.interestfield.dto.response.InterestFieldInfoResponse;
import likelion.portmate.domain.interestfield.entity.InterestField;
import likelion.portmate.domain.interestfield.entity.QInterestField;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Repository
public class InterestFieldQueryDslRepository {

    private final JPAQueryFactory queryFactory;

    public List<InterestFieldInfoResponse> findAllByInterestFieldContaining(List<String> keywords) {
        QInterestField interestField = QInterestField.interestField;

        BooleanExpression whereClause = buildKeywordCondition(keywords, interestField.interestFieldEng, interestField.interestFieldKor);

        List<InterestField> interestFields = queryFactory.selectFrom(interestField)
                .where(whereClause)
                .orderBy(interestField.id.desc())
                .fetch();

        return interestFields.stream()
                .map(this::convertToInterestFieldInfoResponse)
                .collect(Collectors.toList());
    }

    private BooleanExpression buildKeywordCondition(List<String> keywords, StringPath interestFieldEng, StringPath interestFieldKor) {
        if (keywords == null || keywords.isEmpty()) {
            return null;
        }

        BooleanExpression condition = null;

        for (String keyword : keywords) {
            BooleanExpression engContains = interestFieldEng.containsIgnoreCase(keyword);
            BooleanExpression korContains = interestFieldKor.containsIgnoreCase(keyword);
            BooleanExpression keywordCondition = engContains.or(korContains);

            if (condition == null) {
                condition = keywordCondition;
            } else {
                condition = condition.or(keywordCondition);
            }
        }

        return condition;
    }

    private InterestFieldInfoResponse convertToInterestFieldInfoResponse(InterestField interestField) {
        return new InterestFieldInfoResponse(
                interestField.getInterestFieldEng(),
                interestField.getInterestFieldKor()
        );
    }
}
