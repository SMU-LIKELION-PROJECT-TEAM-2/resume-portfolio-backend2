package likelion.portmate.domain.member.entity;

import java.util.Optional;

public interface MemberRepository {

    Member save(Member member);

    Optional<Member> findByLoginId(String loginId);

    Optional<Member> findByMemberId(Long memberId);

    Optional<Member> findByEmail(String email);

    boolean existsByLoginId(String loginId);

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);
}
