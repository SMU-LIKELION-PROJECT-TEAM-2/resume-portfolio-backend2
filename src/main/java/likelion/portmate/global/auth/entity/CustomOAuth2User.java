package likelion.portmate.global.auth.entity;

import likelion.portmate.global.auth.service.dto.AuthAttributes;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;

import java.util.Collection;
import java.util.Map;

@Getter
public class CustomOAuth2User extends DefaultOAuth2User {

    private final AuthAttributes authAttributes;

    public CustomOAuth2User(
            Collection<? extends GrantedAuthority> authorities,
            Map<String, Object> attributes,
            String nameAttributeKey,
            AuthAttributes authAttributes
    ) {
        super(authorities, attributes, nameAttributeKey);
        this.authAttributes = authAttributes;
    }
}
