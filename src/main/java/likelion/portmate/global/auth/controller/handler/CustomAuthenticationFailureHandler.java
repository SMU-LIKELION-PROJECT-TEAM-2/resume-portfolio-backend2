package likelion.portmate.global.auth.controller.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import likelion.portmate.global.auth.controller.exception.AuthExceptionCode;
import likelion.portmate.global.auth.repository.SecurityProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@RequiredArgsConstructor
@Component
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    private final SecurityProperties securityProperties;

    @Override
    public void onAuthenticationFailure(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException exception
    ) throws IOException, ServletException {
        super.setDefaultFailureUrl(securityProperties.loginUrl() + "?error=true&exception=" + AuthExceptionCode.AUTHENTICATION_REQUIRED);
        super.onAuthenticationFailure(request, response, exception);
    }
}
