package santzin.projeta.dev.DTOs.project_user;

public record CreateProjectUserDTO(
        Long userId,
        Long projectId,
        Long positionId
) {

}
