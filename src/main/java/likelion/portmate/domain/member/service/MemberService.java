package likelion.portmate.domain.member.service;

import likelion.portmate.domain.kakao.dto.KakaoUserInfoResponseDto;
import likelion.portmate.domain.kakao.service.KakaoService;
import likelion.portmate.domain.member.controller.exception.DuplicateEmailException;
import likelion.portmate.domain.member.controller.exception.DuplicateLoginIdException;
import likelion.portmate.domain.member.controller.exception.LoginFailedException;
import likelion.portmate.domain.member.controller.exception.MemberNotFoundException;
import likelion.portmate.domain.member.dto.KakaoLoginDto;
import likelion.portmate.domain.member.dto.LoginRequestDto;
import likelion.portmate.domain.member.dto.SignupRequestDto;
import likelion.portmate.domain.member.dto.TokenResponseDto;
import likelion.portmate.domain.member.entity.Member;
import likelion.portmate.domain.member.entity.SocialType;
import likelion.portmate.domain.member.repository.MemberRepository;
import likelion.portmate.global.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository repo;
    private final PasswordEncoder encoder;
    private final JwtUtil jwt;

    @Transactional
    public void signup(SignupRequestDto d) {
        if (repo.findByEmail(d.getEmail()).isPresent())
            throw new DuplicateEmailException();

        repo.save(Member.createLocal(
                d.getLoginId(),
                d.getEmail(),
                encoder.encode(d.getPassword()),
                d.getNickname()
        ));
    }

    @Transactional
    public TokenResponseDto login(LoginRequestDto d) {
        Member m = repo.findByLoginId(d.getLoginId())
                .orElseThrow(MemberNotFoundException::new);

        if (m.getSocialType() != SocialType.LOCAL ||
                !encoder.matches(d.getPassword(), m.getPassword())) {
            throw new LoginFailedException();
        }

        return issueTokens(m);
    }

    @Transactional
    public KakaoLoginDto loginByKakao(String code, KakaoService kakao) {

        String access = kakao.getAccessTokenFromKakao(code);

        KakaoUserInfoResponseDto info = kakao.getUserInfo(access);

        String kakaoId = String.valueOf(info.getId());
        String email   = info.getKakaoAccount().getEmail();
        String nick    = info.getKakaoAccount().getProfile().getNickName();

        Member member = repo.findByKakaoId(kakaoId)
                .orElseGet(() -> repo.save(Member.createKakao(email, nick, kakaoId)));

        TokenResponseDto tokens = issueTokens(member);

        return new KakaoLoginDto(tokens, member.getNickname());
    }

    @Transactional
    public TokenResponseDto issueTokens(Member member) {
        String at = jwt.createAccessToken(member.getId(), member.getLoginId());
        String rt = jwt.createRefreshToken(member.getId(), member.getLoginId());
        member.updateRefreshToken(rt);
        return new TokenResponseDto(at, rt);
    }

    public void validateDuplicateLoginId(String loginId) {
        if (repo.findByLoginId(loginId).isPresent()) {
            throw new DuplicateLoginIdException();
        }
    }
}