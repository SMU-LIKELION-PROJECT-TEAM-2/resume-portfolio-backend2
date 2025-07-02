package likelion.portmate.domain.profilecareer.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record ProfileCareerUpdateRequest(
        Long id,
        @NotBlank String title,
        String description,
        @NotNull LocalDate startDate,
        LocalDate endDate
) {}