package likelion.portmate.global.auth.service;

import likelion.portmate.global.auth.entity.CustomOAuth2User;
import likelion.portmate.global.auth.service.dto.AuthAttributes;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OAuth2UserService extends DefaultOAuth2UserService {

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList("ROLE_USER");

        ClientRegistration clientRegistration = userRequest.getClientRegistration();
        String registrationId = clientRegistration.getRegistrationId();
        String userNameAttributeName = clientRegistration.getProviderDetails()
                .getUserInfoEndpoint()
                .getUserNameAttributeName();

        AuthAttributes authAttributes = AuthAttributes.of(registrationId, oAuth2User.getAttributes());

        return new CustomOAuth2User(authorities, oAuth2User.getAttributes(), userNameAttributeName, authAttributes);
    }
}
