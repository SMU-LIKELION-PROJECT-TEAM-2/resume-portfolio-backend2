package likelion.portmate.domain.job.repository;

import likelion.portmate.domain.job.entity.Job;
import likelion.portmate.domain.member.entity.DepartmentCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobJpaRepository extends JpaRepository<Job, Long> {

    List<Job> findByDepartmentCategory(DepartmentCategory departmentCategory);

}
