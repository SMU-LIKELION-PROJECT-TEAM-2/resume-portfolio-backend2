package likelion.portmate.global.auth.service;

import jakarta.servlet.http.HttpServletRequest;
import likelion.portmate.global.auth.controller.exception.AuthenticationRequiredException;
import likelion.portmate.global.auth.dto.response.LoginResultResponse;
import likelion.portmate.global.auth.entity.RefreshToken;
import likelion.portmate.global.auth.entity.RefreshTokenRepository;
import likelion.portmate.global.jwt.config.TokenProperties;
import likelion.portmate.global.jwt.generator.JwtTokenGenerator;
import likelion.portmate.global.jwt.resolver.JwtTokenResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtTokenGenerator tokenGenerator;
    private final JwtTokenResolver tokenResolver;
    private final TokenProperties tokenProperties;

    @Transactional
    public LoginResultResponse reissueBasedOnRefreshToken(HttpServletRequest request) {
        String refreshToken = tokenResolver.resolveRefreshTokenFromRequest(request)
                .orElseThrow(AuthenticationRequiredException::new);

        RefreshToken savedRefreshToken = validateAndGetSavedRefreshToken(refreshToken);

        return getReissuedTokenResult(savedRefreshToken);
    }

    private LoginResultResponse getReissuedTokenResult(RefreshToken savedRefreshToken) {
        Long memberId = savedRefreshToken.getMemberId();

        String reissuedAccessToken = tokenGenerator.generateAccessToken(memberId);
        String rotatedRefreshToken = this.rotate(savedRefreshToken);

        return new LoginResultResponse(
                reissuedAccessToken, rotatedRefreshToken);
    }

    private RefreshToken validateAndGetSavedRefreshToken(String refreshToken) {
        Long memberId = Long.valueOf(tokenResolver.getSubjectFromToken(refreshToken));
        RefreshToken savedRefreshToken = getByTokenString(refreshToken);
        savedRefreshToken.validateWith(refreshToken, memberId);
        return savedRefreshToken;
    }

    private RefreshToken getByTokenString(String token) {
        return refreshTokenRepository.findByToken(token)
                .orElseThrow(AuthenticationRequiredException::new);
    }


    private String rotate(RefreshToken refreshToken) {
        String reissuedToken = tokenGenerator.generateRefreshToken(refreshToken.getMemberId());
        refreshToken.rotate(reissuedToken);
        refreshToken.updateExpirationIfExpired(tokenProperties.expirationTime().refreshToken());
        refreshTokenRepository.save(refreshToken);

        return reissuedToken;
    }

}
