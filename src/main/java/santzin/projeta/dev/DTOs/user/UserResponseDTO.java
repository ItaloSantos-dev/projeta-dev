package santzin.projeta.dev.DTOs.user;

import jakarta.persistence.Column;
import santzin.projeta.dev.DTOs.hability.HabilityResponseDTO;
import santzin.projeta.dev.DTOs.project.ProjectResponseDTO;
import santzin.projeta.dev.model.enums.UserExperienceLevel;

import java.util.List;

public record UserResponseDTO (
        Long id,
        String name,
        String username,
        UserExperienceLevel experienceLevel,
        String principalStack,
        List<ProjectResponseDTO> myProjects,
        String about,
        String coverUrl,
        String perfilUrl,
        String link1,
        String link2,
        String link3,
        String link4,
        String link5,
        List<HabilityResponseDTO> habilitys,
        Integer notificationsCount,
        List<String> following,
        List<String> followers
){
}
