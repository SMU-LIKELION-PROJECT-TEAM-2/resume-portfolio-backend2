package likelion.portmate.global.auth.repository;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.security")
public record SecurityProperties(
        String loginUrl,
        String redirectUrl,
        String newUserRedirectUrl,
        String onboardingRedirectUrl
) {
}
