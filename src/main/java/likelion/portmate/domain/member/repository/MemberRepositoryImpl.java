package likelion.portmate.domain.member.repository;

import likelion.portmate.domain.member.controller.exception.DuplicateLoginIdException;
import likelion.portmate.domain.member.controller.exception.MemberNotFoundException;
import likelion.portmate.domain.member.entity.Member;
import likelion.portmate.domain.member.entity.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class MemberRepositoryImpl implements MemberRepository {

    private final MemberJpaRepository memberJpaRepository;

    @Override
    public void save(Member member) {
        memberJpaRepository.save(member);
    }

    @Override
    public Member findByLoginId(String loginId) {
        return memberJpaRepository.findByLoginId(loginId)
                .orElseThrow(MemberNotFoundException::new);
    }

    @Override
    public Member findByMemberId(Long memberId) {
        return memberJpaRepository.findById(memberId)
                .orElseThrow(MemberNotFoundException::new);
    }

    @Override
    public Member findByEmail(String email) {
        return memberJpaRepository.findByEmail(email)
                .orElseThrow(MemberNotFoundException::new);
    }

    @Override
    public boolean existsByLoginId(String loginId) {
        return memberJpaRepository.existsByLoginId(loginId);
    }

    @Override
    public boolean existsByEmail(String email) {
        return memberJpaRepository.existsByEmail(email);
    }

    @Override
    public boolean existsByUsername(String username) {
        return memberJpaRepository.existsByUsername(username);
    }

}
