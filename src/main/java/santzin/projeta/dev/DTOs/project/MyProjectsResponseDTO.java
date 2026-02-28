package santzin.projeta.dev.DTOs.project;

import java.util.List;

public record MyProjectsResponseDTO(
        List<ProjectResponseDTO> projectsICreated,
        List<ProjectResponseDTO> projectsIParticipe
) {
}
