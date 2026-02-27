package santzin.projeta.dev.DTOs.project_position;

import santzin.projeta.dev.DTOs.project.ProjectResponseDTO;
import santzin.projeta.dev.DTOs.user.UserResponseDTO;

import java.util.List;

public record ProjectPositionResponseDTO (
        Long id,
        String name,
        ProjectResponseDTO project,
        List<UserResponseDTO> users
){
}
