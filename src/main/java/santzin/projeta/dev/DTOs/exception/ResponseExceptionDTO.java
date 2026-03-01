package santzin.projeta.dev.DTOs.exception;

import org.springframework.http.HttpStatus;

public record ResponseExceptionDTO(
        HttpStatus status,
        String message
) {
}
