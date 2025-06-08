package likelion.portmate.domain.kakao.controller;

import likelion.portmate.domain.kakao.service.KakaoService;
import likelion.portmate.domain.member.dto.KakaoLoginDto;
import likelion.portmate.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth/kakao")
public class KakaoLoginController implements KakaoLoginDocsController {

    private final KakaoService kakaoService;
    private final MemberService memberService;

    @GetMapping("/callback")
    public ResponseEntity<KakaoLoginDto> callback(@RequestParam("code") String code) {
        KakaoLoginDto dto = memberService.loginByKakao(code, kakaoService);

        return ResponseEntity.ok(dto);
    }
}