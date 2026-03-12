package santzin.projeta.dev.DTOs.project_request;

import santzin.projeta.dev.DTOs.project.ProjectResponseDTO;
import santzin.projeta.dev.model.ProjectModel;
import santzin.projeta.dev.model.enums.StatusRequestProject;

import java.time.LocalDate;

public record ProjectRequestResponseDTO(
        Long id,
        ProjectResponseDTO project,
        StatusRequestProject status,
        LocalDate created_at
) {
}
