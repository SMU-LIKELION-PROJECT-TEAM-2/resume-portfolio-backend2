package likelion.portmate.domain.externalActivity.dto.request;

public record ExternalActivitySaveRequest(

        String activityName,

        String affiliation,

        String activityOrganization,

        String description

) {
}
