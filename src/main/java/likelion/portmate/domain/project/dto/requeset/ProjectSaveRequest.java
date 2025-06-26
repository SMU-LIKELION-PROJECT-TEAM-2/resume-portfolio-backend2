package likelion.portmate.domain.project.dto.requeset;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public record ProjectSaveRequest(

        String projectName,

        String organization,

        @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDate startDate,

        @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDate endDate,

        String description

) {
}
