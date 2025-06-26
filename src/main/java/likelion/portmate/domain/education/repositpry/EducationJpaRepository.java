package likelion.portmate.domain.education.repositpry;

import likelion.portmate.domain.education.entity.Education;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EducationJpaRepository extends JpaRepository<Education, Long> {
}
