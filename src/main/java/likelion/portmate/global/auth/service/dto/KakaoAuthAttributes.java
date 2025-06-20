package likelion.portmate.global.auth.service.dto;

import likelion.portmate.domain.member.entity.LoginProvider;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.util.Map;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class KakaoAuthAttributes implements AuthAttributes {

    private final String id;
    private final String email;
    private final LoginProvider provide;

    public static KakaoAuthAttributes of(Map<String, Object> attributes) {
        Map<String, Object> kakaoAccount = (Map<String, Object>)attributes.get("kakao_account");

        return new KakaoAuthAttributes(
                attributes.get("id").toString(),
                (String)kakaoAccount.get("email"),
                LoginProvider.kakao
        );
    }

    @Override
    public String getExternalId() {
        return this.id;
    }

    @Override
    public String getEmail() {
        return this.email;
    }


    @Override
    public LoginProvider getProvider() {
        return this.provide;
    }

}
