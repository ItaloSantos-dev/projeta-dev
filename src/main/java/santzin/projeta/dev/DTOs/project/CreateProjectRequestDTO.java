package santzin.projeta.dev.DTOs.project;

import santzin.projeta.dev.model.enums.ProjectInputType;

public record CreateProjectRequestDTO(
        String title,
        String imgUrl,
        String description,
        String stack,
        ProjectInputType inputType,
        Boolean paid,
        String repositoryLink,
        String principalPosition
) {
}
