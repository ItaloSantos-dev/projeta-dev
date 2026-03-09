package santzin.projeta.dev.DTOs.hability;

public record HabilityResponseDTO (
        Long id,
        String title,
        boolean haveIcon,
        String iconLink
){
}
