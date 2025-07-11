package likelion.portmate.domain.member.controller;

import jakarta.validation.Valid;
import likelion.portmate.domain.member.dto.request.MemberBannerUpdateRequest;
import likelion.portmate.domain.member.dto.request.MemberProfileUpdateRequest;
import likelion.portmate.domain.member.dto.request.MemberSaveRequest;
import likelion.portmate.domain.member.dto.request.MemberSelectCareerProfileSaveRequest;
import likelion.portmate.domain.member.dto.response.MemberProfileViewResponse;
import likelion.portmate.domain.member.dto.response.MemberSaveResponse;
import likelion.portmate.domain.member.service.MemberService;
import likelion.portmate.global.annotation.MemberId;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/members")
@RequiredArgsConstructor
@RestController
public class MemberController implements MemberDocsController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity<MemberSaveResponse> signUp(@RequestBody MemberSaveRequest request) {
        MemberSaveResponse response = memberService.signUp(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/signup-check-duplicate-login-id")
    public ResponseEntity<?> signupCheckDuplicateId(@RequestParam String loginId) {
        memberService.validateLoginId(loginId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/select")
    public ResponseEntity<Void> select(
            @MemberId Long memberId,
            @Valid @RequestBody MemberSelectCareerProfileSaveRequest request
    ) {
        memberService.selectCareerProfile(memberId, request);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/profile/banner")
    public ResponseEntity<Void> updateBanner(
            @MemberId Long memberId,
            @Valid @RequestBody MemberBannerUpdateRequest request
    ) {
        memberService.updateBannerImageUrl(memberId, request.bannerImageUrl());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/profile")
    public ResponseEntity<MemberProfileViewResponse> getProfile(
            @MemberId Long memberId
    ) {
        MemberProfileViewResponse response = memberService.getProfile(memberId);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/profile")
    public ResponseEntity<Void> updateProfile(
            @MemberId Long memberId,
            @Valid @RequestBody MemberProfileUpdateRequest request
    ) {
        memberService.updateProfile(memberId, request);
        return ResponseEntity.noContent().build();
    }

}