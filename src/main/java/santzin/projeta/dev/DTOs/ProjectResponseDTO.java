package santzin.projeta.dev.DTOs;


import santzin.projeta.dev.model.ProjectPositionModel;
import santzin.projeta.dev.model.ProjectUserModel;
import santzin.projeta.dev.model.enums.ProjectInputType;
import santzin.projeta.dev.model.enums.ProjectStatus;

import java.time.LocalDate;
import java.util.List;

public record ProjectResponseDTO(
        Long id,
        String title,
        String imgUrl,
        String description,
        String stack,
        ProjectStatus status,
        ProjectInputType inputType,
        String repositoryLink,
        LocalDate createdAt,
        List<ProjectUserModel> users,
        List<ProjectPositionModel> positions
) {
}
