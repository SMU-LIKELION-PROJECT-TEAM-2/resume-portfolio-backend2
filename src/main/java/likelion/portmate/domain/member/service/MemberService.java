package likelion.portmate.domain.member.service;

import likelion.portmate.domain.kakao.dto.KakaoUserInfoResponseDto;
import likelion.portmate.domain.kakao.service.KakaoService;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    /* 1) 회원가입 */
    @Transactional
    public void signup(SignupRequestDto d) {
        if (repo.findByLoginId(d.getLoginId()).isPresent())
            throw new IllegalArgumentException("중복 ID");
        if (repo.findByEmail(d.getEmail()).isPresent())
            throw new IllegalArgumentException("중복 이메일");

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
                .orElseThrow(() -> new IllegalArgumentException("가입 안됨"));

        if (m.getSocialType() != SocialType.LOCAL ||
                !encoder.matches(d.getPassword(), m.getPassword()))
            throw new IllegalArgumentException("로그인 실패");

        return issueTokens(m);
    }

    /* 3) 카카오 로그인 */
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
}