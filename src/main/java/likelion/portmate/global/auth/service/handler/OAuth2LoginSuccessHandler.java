package likelion.portmate.global.auth.service.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import likelion.portmate.global.auth.controller.exception.AlreadyRegisteredMemberException;
import likelion.portmate.global.auth.dto.response.OauthLoginResultResponse;
import likelion.portmate.global.auth.entity.CustomOAuth2User;
import likelion.portmate.global.auth.repository.SecurityProperties;
import likelion.portmate.global.auth.service.OAuthLoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

import static likelion.portmate.global.auth.controller.exception.AuthExceptionCode.ALREADY_REGISTERED_MEMBER;

@RequiredArgsConstructor
@Component
public class OAuth2LoginSuccessHandler implements AuthenticationSuccessHandler {

    private final OAuthLoginService oAuth2LoginService;
    private final SecurityProperties securityProperties;
    private final ObjectMapper objectMapper;

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication
    ) throws IOException {
        try {
            OauthLoginResultResponse result = resolveLoginResultFromAuthentication(authentication);

            sendSuccessResponse(response, result);
        } catch (AlreadyRegisteredMemberException e) {
            sendAlreadyExistUserResponse(response);
        }
    }

    private OauthLoginResultResponse resolveLoginResultFromAuthentication(Authentication authentication) {
        CustomOAuth2User oAuth2User = (CustomOAuth2User) authentication.getPrincipal();
        return oAuth2LoginService.handleLoginSuccess(oAuth2User.getAuthAttributes());
    }

    private void sendSuccessResponse(HttpServletResponse response, OauthLoginResultResponse result) throws IOException {
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");

        objectMapper.writeValue(response.getWriter(), result);
    }

    private void sendAlreadyExistUserResponse(HttpServletResponse response) throws IOException {
        response.setStatus(HttpServletResponse.SC_CONFLICT);
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");

        Map<String, Object> errorResponse = Map.of(
                "errorCode", ALREADY_REGISTERED_MEMBER.getCode(),
                "message", ALREADY_REGISTERED_MEMBER.getMessage()
        );

        objectMapper.writeValue(response.getWriter(), errorResponse);
    }

}
