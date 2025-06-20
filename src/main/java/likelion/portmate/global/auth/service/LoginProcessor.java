package likelion.portmate.global.auth.service;

import likelion.portmate.global.auth.dto.response.LoginResultResponse;
import likelion.portmate.global.auth.entity.RefreshToken;
import likelion.portmate.global.auth.entity.RefreshTokenRepository;
import likelion.portmate.global.jwt.config.TokenProperties;
import likelion.portmate.global.jwt.generator.JwtTokenGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class LoginProcessor {

    private final JwtTokenGenerator tokenGenerator;
    private final RefreshTokenRepository refreshTokenRepository;
    private final TokenProperties tokenProperties;

    public LoginResultResponse generateLoginResult(Long memberId) {
        String accessToken = tokenGenerator.generateAccessToken(memberId);
        String refreshToken = tokenGenerator.generateRefreshToken(memberId);

        RefreshToken refreshTokenEntity = refreshTokenRepository.findByMemberId(memberId)
                .orElse(RefreshToken.of(memberId, refreshToken, tokenProperties.expirationTime().refreshToken()));

        refreshTokenEntity.rotate(refreshToken);
        refreshTokenRepository.save(refreshTokenEntity);

        return new LoginResultResponse(accessToken, refreshToken);
    }

}
