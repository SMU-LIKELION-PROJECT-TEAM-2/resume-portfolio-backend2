package likelion.portmate.global.auth.service;

import likelion.portmate.domain.member.entity.Member;
import likelion.portmate.domain.member.entity.MemberRepository;
import likelion.portmate.global.auth.controller.exception.AlreadyRegisteredMemberException;
import likelion.portmate.global.auth.dto.response.LoginResultResponse;
import likelion.portmate.global.auth.dto.response.OauthLoginResultResponse;
import likelion.portmate.global.auth.service.dto.AuthAttributes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class OAuthLoginService {

    private final MemberRepository memberRepository;
    private final LoginProcessor loginProcessor;

    @Transactional
    public OauthLoginResultResponse handleLoginSuccess(AuthAttributes attributes) {
        String email = attributes.getEmail();

        Member member = memberRepository.findByEmail(email)
                .map(existing -> handleExistUser(existing, attributes))
                .orElseGet(() -> handleFirstLogin(attributes));

        boolean isFirstLogin = member.getCreatedAt().equals(member.getUpdatedAt()); // 또는 별도 플래그로 관리
        LoginResultResponse tokenResult = loginProcessor.generateLoginResult(member.getId());

        return OauthLoginResultResponse.builder()
                .accessToken(tokenResult.accessToken())
                .refreshToken(tokenResult.refreshToken())
                .isFirstLogin(isFirstLogin)
                .memberId(member.getId())
                .build();
    }

    private Member handleExistUser(Member member, AuthAttributes attributes) {
        if (member.hasDifferentProviderWithEmail(attributes.getEmail(), attributes.getExternalId())) {
            throw new AlreadyRegisteredMemberException();
        }
        return member;
    }

    private Member handleFirstLogin(AuthAttributes attributes) {
        Member member = Member.from(attributes);
        return memberRepository.save(member);
    }

}
