package likelion.portmate.global.auth.entity;

import java.util.Optional;

public interface RefreshTokenRepository {

    Optional<RefreshToken> findByToken(String token);

    void save(RefreshToken refreshToken);

    Optional<RefreshToken> findByMemberId(Long id);

    void deleteByMemberId(Long memberId);

}
