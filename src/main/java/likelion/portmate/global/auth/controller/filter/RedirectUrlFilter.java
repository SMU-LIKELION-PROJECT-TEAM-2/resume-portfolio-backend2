package likelion.portmate.global.auth.controller.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Component
public class RedirectUrlFilter extends OncePerRequestFilter {

    public static final String REDIRECT_URL_QUERY_PARAM = "redirectUrl";
    private static final List<String> REDIRECT_URL_INJECTION_PATTERNS = List.of(
            "/oauth2/authorization/.*"
    );

    private final ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        if (isRedirectRequest(request)) {
            String redirectUri = request.getParameter(REDIRECT_URL_QUERY_PARAM);

            if (StringUtils.hasText(redirectUri)) {
                response.setContentType("application/json");
                response.setCharacterEncoding("utf-8");

                Map<String, String> responseBody = Map.of("redirectUrl", redirectUri);
                objectMapper.writeValue(response.getWriter(), responseBody);

                return;
            }
        }

        filterChain.doFilter(request, response);
    }

    private boolean isRedirectRequest(HttpServletRequest request) {
        return REDIRECT_URL_INJECTION_PATTERNS.stream()
                .anyMatch(request.getRequestURI()::matches);
    }

}
