package likelion.portmate.global.auth.dto.response;

import lombok.Builder;

@Builder
public record OauthLoginResultResponse(

        String accessToken,
        String refreshToken,
        boolean isFirstLogin,
        Long memberId

) {
}
