package likelion.portmate.domain.project.repository;

import likelion.portmate.domain.project.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectJpaRepository extends JpaRepository<Project, Long> {
}
