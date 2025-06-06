package likelion.portmate.domain.member.controller;

import likelion.portmate.domain.member.dto.LoginRequestDto;
import likelion.portmate.domain.member.dto.SignupRequestDto;
import likelion.portmate.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")          // <-- 꼭 /auth 로 시작
@RequiredArgsConstructor
public class AuthController {

    private final MemberService memberService;

    @PostMapping("/signup")       // <-- POST /auth/signup
    public ResponseEntity<?> signup(@RequestBody SignupRequestDto dto) {
        memberService.signup(dto);
        return ResponseEntity.ok("회원가입 성공");
    }

    @PostMapping("/login")        // <-- POST /auth/login
    public ResponseEntity<?> login(@RequestBody LoginRequestDto dto) {
        return ResponseEntity.ok(memberService.login(dto));
    }
}
