package likelion.portmate.domain.job.controller;

import likelion.portmate.domain.job.dto.response.JobInfoResponse;
import likelion.portmate.domain.job.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/jobs")
@RestController
public class JobController implements JobDocsController{

    private final JobService jobService;

    @GetMapping
    public ResponseEntity<List<JobInfoResponse>> getJobs(@RequestParam("type") String departmentCategory) {
        List<JobInfoResponse> response = jobService.getJobsByDepartmentCategory(departmentCategory.toUpperCase());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/search")
    public ResponseEntity<List<JobInfoResponse>> searchJobs(
            @RequestParam List<String> keywords
    ) {
        List<JobInfoResponse> response = jobService.searchJobs(keywords);
        return ResponseEntity.ok(response);
    }

}
