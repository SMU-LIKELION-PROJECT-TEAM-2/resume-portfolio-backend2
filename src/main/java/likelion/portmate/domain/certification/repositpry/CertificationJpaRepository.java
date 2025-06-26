package likelion.portmate.domain.certification.repositpry;

import likelion.portmate.domain.certification.entity.Certification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CertificationJpaRepository extends JpaRepository<Certification, Long> {
}
