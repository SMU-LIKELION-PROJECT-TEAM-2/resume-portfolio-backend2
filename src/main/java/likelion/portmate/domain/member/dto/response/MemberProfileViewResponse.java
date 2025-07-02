package likelion.portmate.domain.member.dto.response;

import likelion.portmate.domain.job.entity.Job;
import likelion.portmate.domain.member.entity.Member;
import likelion.portmate.domain.profilecareer.dto.response.ProfileCareerResponse;
import likelion.portmate.domain.profilecareer.entity.ProfileCareer;
import likelion.portmate.domain.skill.entity.Skill;

import java.util.List;

public record MemberProfileViewResponse(
        String username,
        String bannerImageUrl,
        String introduction,
        String address,
        String jobName,
        String favoriteSkillEng,
        String favoriteSkillKor,
        List<ProfileCareerResponse> profileCareers
) {
    public static MemberProfileViewResponse from(Member member, List<ProfileCareer> careers) {
        Job job = member.getJob();
        Skill skill = member.getSkill();

        return new MemberProfileViewResponse(
                member.getUsername(),
                member.getBannerImageUrl(),
                member.getIntroduction(),
                member.getAddress(),
                job != null ? job.getJobName() : null,
                skill != null ? skill.getFavoriteSkillEng() : null,
                skill != null ? skill.getFavoriteSkillKor() : null,
                careers.stream().map(ProfileCareerResponse::from).toList()
        );
    }
}