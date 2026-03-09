package santzin.projeta.dev.DTOs.hability;

public record CreateHabilityRequestDTO(
        String title,
        boolean haveIcon,
        String iconLink
) {
}
