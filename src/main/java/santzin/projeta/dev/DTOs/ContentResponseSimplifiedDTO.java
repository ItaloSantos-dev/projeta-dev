package santzin.projeta.dev.DTOs;

import java.time.OffsetDateTime;
import java.util.UUID;

public record ContentResponseSimplifiedDTO(UUID id, String ownerUsername, String slug, String title, OffsetDateTime publishedAt ){
}
