package likelion.portmate.domain.member.service;

import likelion.portmate.domain.member.controller.exception.DuplicateEmailException;
import likelion.portmate.domain.member.controller.exception.DuplicateLoginIdException;
import likelion.portmate.domain.member.controller.exception.DuplicateUsernameException;
import likelion.portmate.domain.member.dto.request.MemberSaveRequest;
import likelion.portmate.domain.member.dto.response.MemberSaveResponse;
import likelion.portmate.domain.member.entity.Member;
import likelion.portmate.domain.member.entity.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public MemberSaveResponse signUp(MemberSaveRequest request) {
        validateSignUp(request.email(), request.password());

        Member member = Member.builder()
                .loginId(request.loginId())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .username(request.username())
                .build();
        memberRepository.save(member);

        return MemberSaveResponse.builder()
                .memberId(member.getId())
                .build();
    }

    public void validateLoginId(String loginId) {
        if (memberRepository.existsByLoginId(loginId)) {
            throw new DuplicateLoginIdException();
        }
    }

    private void validateSignUp(String email, String username) {
        validateEmail(email);
        validateUsername(username);
    }

    private void validateEmail(String email) {
        if (memberRepository.existsByEmail(email)) {
            throw new DuplicateEmailException();
        }
    }

    private void validateUsername(String username) {
        if (memberRepository.existsByUsername(username)) {
            throw new DuplicateUsernameException();
        }
    }
}