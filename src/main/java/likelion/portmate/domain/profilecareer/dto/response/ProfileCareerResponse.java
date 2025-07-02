package likelion.portmate.domain.profilecareer.dto.response;

import likelion.portmate.domain.profilecareer.entity.ProfileCareer;

import java.time.LocalDate;

public record ProfileCareerResponse(
        Long id,
        String title,
        String description,
        LocalDate startDate,
        LocalDate endDate
) {
    public static ProfileCareerResponse from(ProfileCareer profileCareer) {
        return new ProfileCareerResponse(
                profileCareer.getId(),
                profileCareer.getTitle(),
                profileCareer.getDescription(),
                profileCareer.getStartDate(),
                profileCareer.getEndDate()
        );
    }
}