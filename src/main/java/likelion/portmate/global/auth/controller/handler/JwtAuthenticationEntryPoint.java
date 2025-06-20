package likelion.portmate.global.auth.controller.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import likelion.portmate.global.exception.ExceptionResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static com.nimbusds.oauth2.sdk.http.HTTPResponse.SC_UNAUTHORIZED;
import static likelion.portmate.global.auth.controller.exception.AuthExceptionCode.AUTHENTICATION_REQUIRED;

@RequiredArgsConstructor
@Slf4j
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper;

    @Override
    public void commence(
            HttpServletRequest request, HttpServletResponse response, AuthenticationException exception
    ) throws IOException {
        setResponseBodyBasicInfo(response);
        objectMapper.writeValue(response.getOutputStream(), ExceptionResponse.from(AUTHENTICATION_REQUIRED));
    }

    private void setResponseBodyBasicInfo(HttpServletResponse response) {
        response.setStatus(SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
    }
}
