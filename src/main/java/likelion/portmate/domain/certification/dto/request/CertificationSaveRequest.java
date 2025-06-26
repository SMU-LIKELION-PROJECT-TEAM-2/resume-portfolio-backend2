package likelion.portmate.domain.certification.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public record CertificationSaveRequest(

        String name,

        String scoreOrGrade,

        String issuingOrganization,

        @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDate acquisitionDate

) {
}
