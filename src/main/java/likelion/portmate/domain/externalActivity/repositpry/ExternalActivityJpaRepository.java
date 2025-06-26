package likelion.portmate.domain.externalActivity.repositpry;

import likelion.portmate.domain.externalActivity.entity.ExternalActivity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExternalActivityJpaRepository extends JpaRepository<ExternalActivity, Long> {
}
