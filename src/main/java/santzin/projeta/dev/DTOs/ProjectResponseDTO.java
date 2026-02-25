package santzin.projeta.dev.DTOs;


import santzin.projeta.dev.model.enums.ProjectInputType;
import santzin.projeta.dev.model.enums.ProjectStatus;

public record ProjectResponseDTO(Long id, String title, String description, ProjectStatus status, ProjectInputType inputType) {
}
