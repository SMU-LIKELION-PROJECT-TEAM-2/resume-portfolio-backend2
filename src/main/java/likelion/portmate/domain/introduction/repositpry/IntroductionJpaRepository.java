package likelion.portmate.domain.introduction.repositpry;

import likelion.portmate.domain.introduction.entity.Introduction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IntroductionJpaRepository extends JpaRepository<Introduction, Long> {
}
