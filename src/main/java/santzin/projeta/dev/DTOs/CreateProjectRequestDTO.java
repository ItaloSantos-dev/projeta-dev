package santzin.projeta.dev.DTOs;

import santzin.projeta.dev.model.enums.ProjectInputType;
import santzin.projeta.dev.model.enums.ProjectStatus;

import java.time.LocalDate;

public record CreateProjectRequestDTO(
        String title,
        String imgUrl,
        String description,
        String stack,
        ProjectInputType inputType,
        String repositoryLink
) {
}
