package likelion.portmate.global.auth.repository;

import likelion.portmate.global.auth.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenJpaRepository extends JpaRepository<RefreshToken, Long> {

    Optional<RefreshToken> findByToken(String token);

    Optional<RefreshToken> findFirstByMemberIdOrderByIdDesc(Long id);

    void deleteByMemberId(Long memberId);

}
