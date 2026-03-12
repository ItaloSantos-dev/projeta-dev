package santzin.projeta.dev.DTOs.project_request;

import santzin.projeta.dev.model.enums.StatusRequestProject;

public record UpdateProjectRequestRequestDTO (
        StatusRequestProject newStatus,
        Long positionId
){
}
