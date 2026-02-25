package santzin.projeta.dev.DTOs;

import santzin.projeta.dev.model.enums.ProjectInputType;
import santzin.projeta.dev.model.enums.ProjectStatus;

public record UpdateProjectRequestDTO(
        String title,
        String imgUrl,
        String description,
        ProjectStatus status,
        String stack,
        ProjectInputType inputType,
        String repositoryLink
) {
}
