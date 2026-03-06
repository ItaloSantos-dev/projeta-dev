package santzin.projeta.dev.DTOs.auth;

import santzin.projeta.dev.model.enums.UserExperienceLevel;

public record RegisterRequestDTO(
        String name,
        String username,
        String email,
        String password,
        String telephoneNumber,
        UserExperienceLevel experienceLevel,
        String principalStack
) {
}
