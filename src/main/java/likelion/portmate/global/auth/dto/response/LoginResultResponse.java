package likelion.portmate.global.auth.dto.response;

public record LoginResultResponse(

        String accessToken,
        String refreshToken

) {
}
