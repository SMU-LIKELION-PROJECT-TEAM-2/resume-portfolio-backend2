package likelion.portmate.domain.job.entity;

import likelion.portmate.domain.job.dto.response.JobInfoResponse;
import likelion.portmate.domain.member.entity.DepartmentCategory;

import java.util.List;

public interface JobRepository {

    List<Job> findByDepartmentCategory(DepartmentCategory departmentCategory);

    Job findByJobId(Long jobId);

    List<JobInfoResponse> findAllByJobNameContaining(List<String> keywords);

}
