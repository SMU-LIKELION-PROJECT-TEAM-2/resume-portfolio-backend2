package likelion.portmate.domain.member.service;

import likelion.portmate.domain.interestfield.entity.InterestField;
import likelion.portmate.domain.interestfield.entity.InterestFieldRepository;
import likelion.portmate.domain.job.entity.Job;
import likelion.portmate.domain.job.entity.JobRepository;
import likelion.portmate.domain.member.controller.exception.DuplicateEmailException;
import likelion.portmate.domain.member.controller.exception.DuplicateLoginIdException;
import likelion.portmate.domain.member.controller.exception.DuplicateUsernameException;
import likelion.portmate.domain.member.controller.exception.MemberNotFoundException;
import likelion.portmate.domain.member.dto.request.MemberSaveRequest;
import likelion.portmate.domain.member.dto.request.MemberSelectCareerProfileSaveRequest;
import likelion.portmate.domain.member.dto.response.MemberSaveResponse;
import likelion.portmate.domain.member.entity.DepartmentCategory;
import likelion.portmate.domain.member.entity.Member;
import likelion.portmate.domain.member.entity.MemberRepository;
import likelion.portmate.domain.skill.entity.Skill;
import likelion.portmate.domain.skill.entity.SkillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JobRepository jobRepository;
    private final InterestFieldRepository interestFieldRepository;
    private final SkillRepository skillRepository;

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

    @Transactional
    public void selectCareerProfile(Long memberId, MemberSelectCareerProfileSaveRequest request) {
        Member member = memberRepository.findByMemberId(memberId)
                .orElseThrow(MemberNotFoundException::new);

        Job job = jobRepository.findByJobId(request.jobId());

        InterestField interestField = interestFieldRepository.findByInterestFieldId(request.interestFieldId());

        Skill skill = skillRepository.findBySkillId(request.skillId());

        member.selectCareerProfile(
                DepartmentCategory.valueOf(request.departmentCategory()),
                job,
                interestField,
                skill
        );
    }

    public void updateBannerImageUrl(Long memberId, String bannerImageUrl) {
        Member member = memberRepository.findByMemberId(memberId)
                .orElseThrow(MemberNotFoundException::new);
        member.changeBannerImageUrl(bannerImageUrl);
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