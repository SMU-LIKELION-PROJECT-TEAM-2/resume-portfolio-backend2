package likelion.portmate.domain.career.repositpry;

import likelion.portmate.domain.career.entity.Career;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CareerJpaRepository extends JpaRepository<Career, Long> {
}
