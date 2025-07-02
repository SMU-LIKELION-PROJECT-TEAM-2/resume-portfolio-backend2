package likelion.portmate.domain.profilecareer.repository;

import likelion.portmate.domain.profilecareer.entity.ProfileCareer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProfileCareerJpaRepository extends JpaRepository<ProfileCareer, Long> {

    List<ProfileCareer> findAllByMemberId(Long memberId);
}