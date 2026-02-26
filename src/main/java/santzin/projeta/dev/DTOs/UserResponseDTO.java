package santzin.projeta.dev.DTOs;

import santzin.projeta.dev.model.enums.UserExperienceLevel;

public record UserResponseDTO (
        String name,
        String email,
        UserExperienceLevel experienceLevel,
        String telephoneNumber,
        String principalStack
){
}
