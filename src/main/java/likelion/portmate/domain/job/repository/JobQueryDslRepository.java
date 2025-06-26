package likelion.portmate.domain.job.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.StringPath;
import com.querydsl.jpa.impl.JPAQueryFactory;
import likelion.portmate.domain.job.dto.response.JobInfoResponse;
import likelion.portmate.domain.job.entity.Job;
import likelion.portmate.domain.job.entity.QJob;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Repository
public class JobQueryDslRepository {

    private final JPAQueryFactory queryFactory;

    public List<JobInfoResponse> findAllByJobNameContaining(List<String> keywords) {
        QJob job = QJob.job;

        BooleanExpression whereClause = buildKeywordCondition(keywords, job.jobName);

        List<Job> jobs = queryFactory.selectFrom(job)
                .where(whereClause)
                .orderBy(job.id.desc())
                .fetch();

        return jobs.stream()
                .map(this::convertToJobInfoResponse)
                .collect(Collectors.toList());
    }

    private BooleanExpression buildKeywordCondition(List<String> keywords, StringPath field) {
        if (keywords == null || keywords.isEmpty()) {
            return null;
        }

        BooleanExpression result = field.containsIgnoreCase(keywords.get(0));
        for (int i = 1; i < keywords.size(); i++) {
            result = result.or(field.containsIgnoreCase(keywords.get(i)));
        }
        return result;
    }

    private JobInfoResponse convertToJobInfoResponse(Job job) {
        return new JobInfoResponse(job.getJobName());
    }

}
