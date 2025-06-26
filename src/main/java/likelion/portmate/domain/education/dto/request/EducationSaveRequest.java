package likelion.portmate.domain.education.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import likelion.portmate.domain.education.entity.EducationType;
import likelion.portmate.domain.education.entity.EnrollmentStatus;

import java.time.LocalDate;

public record EducationSaveRequest(

        EducationType educationType,

        String organization,

        String majorName,

        String majorCategory,

        @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDate startDate,

        @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDate endDate,

        EnrollmentStatus enrollmentStatus

) {
}
