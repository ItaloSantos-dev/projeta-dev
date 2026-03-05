package santzin.projeta.dev.DTOs.project;


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
        ProjectStatus status,
        ProjectInputType inputType,
        String repositoryLink,
        LocalDate createdAt,
        List<UserResponseDTO> users,
        List<String> positions
) {
}
