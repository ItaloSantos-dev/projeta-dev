package santzin.projeta.dev.DTOs.user;

import santzin.projeta.dev.DTOs.project.ProjectResponseDTO;
import santzin.projeta.dev.model.enums.UserExperienceLevel;

import java.util.List;

public record UserResponseDTO (
        String name,
        String username,
        UserExperienceLevel experienceLevel,
        String principalStack,
        List<ProjectResponseDTO> myProjects
){
}
