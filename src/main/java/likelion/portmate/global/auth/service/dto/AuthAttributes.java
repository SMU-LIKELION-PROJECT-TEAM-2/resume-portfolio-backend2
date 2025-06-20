package likelion.portmate.global.auth.service.dto;

import likelion.portmate.domain.member.entity.LoginProvider;

import java.util.Map;

public interface AuthAttributes {

    String getExternalId();

    String getEmail();

    LoginProvider getProvider();

    static AuthAttributes of(String providerId, Map<String, Object> attributes) {
        if (LoginProvider.kakao.isProviderOf(providerId)) {
            return KakaoAuthAttributes.of(attributes);
        }
        throw new IllegalArgumentException("Unsupported id: " + providerId);
    }

}
