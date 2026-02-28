package santzin.projeta.dev.DTOs.project_user;

public record ProjectUserResponseDTO(
        Long id,
        String userName,
        String projectName,
        String position
) {
}
