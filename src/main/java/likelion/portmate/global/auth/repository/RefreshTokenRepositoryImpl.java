package likelion.portmate.global.auth.repository;

import likelion.portmate.global.auth.entity.RefreshToken;
import likelion.portmate.global.auth.entity.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class RefreshTokenRepositoryImpl implements RefreshTokenRepository {

    private final RefreshTokenJpaRepository refreshTokenJpaRepository;

    @Override
    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenJpaRepository.findByToken(token);
    }

    @Override
    public Optional<RefreshToken> findByMemberId(Long id) {
        return refreshTokenJpaRepository.findFirstByMemberIdOrderByIdDesc(id);
    }

    @Override
    public void save(RefreshToken refreshToken) {
        refreshTokenJpaRepository.save(refreshToken);
    }

    @Override
    public void deleteByMemberId(Long memberId) {
        refreshTokenJpaRepository.deleteByMemberId(memberId);
    }

}
