package likelion.portmate.domain.job.service;

import likelion.portmate.domain.job.dto.response.JobInfoResponse;
import likelion.portmate.domain.job.entity.Job;
import likelion.portmate.domain.job.entity.JobRepository;
import likelion.portmate.domain.member.entity.DepartmentCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class JobService {

    private final JobRepository jobRepository;

    @Transactional(readOnly = true)
    public List<JobInfoResponse> getJobsByDepartmentCategory(String departmentCategory) {
        List<Job> jobs = jobRepository.findByDepartmentCategory(DepartmentCategory.valueOf(departmentCategory));

        return jobs.stream()
                .map(job -> JobInfoResponse.builder()
                        .jobName(job.getJobName())
                        .build()
                )
                .toList();
    }

    @Transactional(readOnly = true)
    public List<JobInfoResponse> searchJobs(List<String> keywords) {
        return jobRepository.findAllByJobNameContaining(keywords);
    }

}
