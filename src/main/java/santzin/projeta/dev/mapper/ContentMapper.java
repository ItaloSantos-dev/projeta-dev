package santzin.projeta.dev.mapper;

import org.springframework.stereotype.Component;
import santzin.projeta.dev.DTOs.ContentResponseDTO;
import santzin.projeta.dev.DTOs.ContentResponseSimplifiedDTO;

@Component
public class ContentMapper {
    public ContentResponseSimplifiedDTO responseToResponseSimplified(ContentResponseDTO responseDTO) {
        return new ContentResponseSimplifiedDTO(
                responseDTO.id(),
                responseDTO.ownerUsername(),
                responseDTO.slug(),
                responseDTO.title(),
                responseDTO.publishedAt()
        );
    }
}
