package santzin.projeta.dev.DTOs.project;


import santzin.projeta.dev.DTOs.project_request.ProjectRequestResponseDTO;
import santzin.projeta.dev.DTOs.user.UserResponseDTO;
import santzin.projeta.dev.model.enums.ProjectInputType;
import santzin.projeta.dev.model.enums.ProjectStatus;

import java.time.LocalDate;
import java.util.List;

public record ProjectResponseDTO(
        Long id,
        String title,
        String slug,
        String imgUrl,
        String description,
        String stack,
        String creator,
        ProjectStatus status,
        ProjectInputType inputType,
        Boolean paid,
        String repositoryLink,
        LocalDate createdAt,
        Integer requestsCount,
        List<String > contibutors,
        List<String> positions
) {
}
