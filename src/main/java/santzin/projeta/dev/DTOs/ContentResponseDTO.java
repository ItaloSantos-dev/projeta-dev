package santzin.projeta.dev.infra.external.DTOs;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.UUID;

public record ContentResponseDTO(
        UUID id,

        @JsonProperty("owner_id")
        UUID ownerId,

        @JsonProperty("parent_id")
        UUID parentId,

        String slug,
        String title,
        String status,

        @JsonProperty("source_url")
        String sourceUrl,

        @JsonProperty("created_at")
        OffsetDateTime createdAt,

        @JsonProperty("updated_at")
        OffsetDateTime updatedAt,

        @JsonProperty("published_at")
        OffsetDateTime publishedAt,

        @JsonProperty("deleted_at")
        OffsetDateTime deletedAt,

        Integer tabcoins,

        @JsonProperty("tabcoins_credit")
        Integer tabcoinsCredit,

        @JsonProperty("tabcoins_debit")
        Integer tabcoinsDebit,

        @JsonProperty("owner_username")
        String ownerUsername,

        @JsonProperty("children_deep_count")
        Integer childrenDeepCount,

        String type
) {
}
