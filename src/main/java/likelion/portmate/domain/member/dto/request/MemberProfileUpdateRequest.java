package likelion.portmate.domain.member.dto.request;

import likelion.portmate.domain.profilecareer.dto.request.ProfileCareerUpdateRequest;

import java.util.List;

public record MemberProfileUpdateRequest(
        String username,
        String introduction,
        String address,
        List<ProfileCareerUpdateRequest> profileCareers
) {
}