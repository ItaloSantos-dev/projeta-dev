package santzin.projeta.dev.DTOs.content;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.OffsetDateTime;
import java.util.UUID;

public record ContentResponseSimplifiedDTO(
        UUID id, String ownerUsername, String slug, String title, OffsetDateTime publishedAt, Integer tabcoinsCredit,
        Integer tabcoinsDebit, String body
){
}
