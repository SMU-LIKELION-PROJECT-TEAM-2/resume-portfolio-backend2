package likelion.portmate.domain.career.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import likelion.portmate.domain.career.entity.WorkType;

import java.time.LocalDate;

public record CareerSaveRequest(

        String companyName,

        @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDate startDate,

        @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDate endDate,

        String position,

        String departmentName,

        WorkType workType,

        String responsibilities

) {
}
