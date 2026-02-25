package santzin.projeta.dev.DTOs;

import santzin.projeta.dev.model.enums.UserExperienceLevel;

public record RegisterRequestDTO(
        String name,
        String email,
        String password,
        String telephoneNumber,
        UserExperienceLevel experienceLevel,
        String principalStack
) {
}
