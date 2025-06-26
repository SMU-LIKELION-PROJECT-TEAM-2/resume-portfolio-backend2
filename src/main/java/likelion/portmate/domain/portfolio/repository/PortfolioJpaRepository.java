package likelion.portmate.domain.portfolio.repository;

import likelion.portmate.domain.portfolio.entity.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PortfolioJpaRepository extends JpaRepository<Portfolio, Long> {
}
