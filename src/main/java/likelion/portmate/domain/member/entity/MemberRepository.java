package likelion.portmate.domain.member.entity;

public interface MemberRepository {

    void save(Member member);

    Member findByLoginId(String loginId);

    Member findByMemberId(Long memberId);

    Member findByEmail(String email);

    boolean existsByLoginId(String loginId);

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);
}
