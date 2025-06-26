package likelion.portmate.domain.resume.repository;

import likelion.portmate.domain.resume.entity.Resume;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResumeJpaRepository extends JpaRepository<Resume, Long> {
}
