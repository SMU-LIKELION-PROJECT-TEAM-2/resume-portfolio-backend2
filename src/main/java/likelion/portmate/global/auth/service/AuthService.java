package likelion.portmate.global.auth.service;

import likelion.portmate.domain.member.controller.exception.MemberNotFoundException;
import likelion.portmate.domain.member.entity.Member;
import likelion.portmate.domain.member.entity.MemberRepository;
import likelion.portmate.global.auth.dto.response.LoginResultResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final LoginProcessor loginProcessor;

    @Transactional
    public LoginResultResponse login(String loginId, String password) {
        Member member = memberRepository.findByLoginId(loginId)
                .orElseThrow(MemberNotFoundException::new);
        member.checkPassword(passwordEncoder, password);

        return loginProcessor.generateLoginResult(member.getId());
    }

}
