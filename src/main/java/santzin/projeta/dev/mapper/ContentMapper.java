package santzin.projeta.dev.mapper;

import org.springframework.stereotype.Component;
import santzin.projeta.dev.DTOs.content.ContentResponseDTO;
import santzin.projeta.dev.DTOs.content.ContentResponseSimplifiedDTO;

@Component
public class ContentMapper {
    public ContentResponseSimplifiedDTO responseToResponseSimplified(ContentResponseDTO responseDTO) {
        return new ContentResponseSimplifiedDTO(
                responseDTO.id(),
                responseDTO.ownerUsername(),
                responseDTO.slug(),
                responseDTO.title(),
                responseDTO.publishedAt(),
                responseDTO.tabcoinsCredit(),
                responseDTO.tabcoinsDebit(),
                responseDTO.body()
        );
    }
}
