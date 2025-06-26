package likelion.portmate.domain.job.repository;

import likelion.portmate.domain.job.controller.exception.JobNotFoundException;
import likelion.portmate.domain.job.dto.response.JobInfoResponse;
import likelion.portmate.domain.job.entity.Job;
import likelion.portmate.domain.job.entity.JobRepository;
import likelion.portmate.domain.member.entity.DepartmentCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class JobRepositoryImpl implements JobRepository {

    private final JobJpaRepository jobJpaRepository;
    private final JobQueryDslRepository jobQueryDslRepository;

    @Override
    public List<Job> findByDepartmentCategory(DepartmentCategory departmentCategory) {
        return jobJpaRepository.findByDepartmentCategory(departmentCategory);
    }

    @Override
    public Job findByJobId(Long jobId) {
        return jobJpaRepository.findById(jobId)
                .orElseThrow(JobNotFoundException::new);
    }

    @Override
    public List<JobInfoResponse> findAllByJobNameContaining(List<String> keywords) {
        return jobQueryDslRepository.findAllByJobNameContaining(keywords);
    }

}
