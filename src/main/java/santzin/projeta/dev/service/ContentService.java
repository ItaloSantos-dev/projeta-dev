package santzin.projeta.dev.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import santzin.projeta.dev.DTOs.content.ContentRequestDTO;
import santzin.projeta.dev.DTOs.content.ContentResponseSimplifiedDTO;
import santzin.projeta.dev.DTOs.content.ContentResponseDTO;
import santzin.projeta.dev.infra.external.NewsLetterAPI;
import santzin.projeta.dev.mapper.ContentMapper;
import santzin.projeta.dev.model.enums.StrategyFindContents;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class ContentService {
    @Autowired
    private NewsLetterAPI newsLetterAPI;

    @Autowired
    private ContentMapper contentMapper;

    public List<ContentResponseSimplifiedDTO> getMostPopularContents(
            Integer page,
            UUID lastContentId,
            StrategyFindContents strategy
    ){
        int pageFinal = page*3;
        int loopInPage = pageFinal-2;
        List<ContentResponseSimplifiedDTO> response = new ArrayList<>();

        while ( loopInPage <= pageFinal || response.size()<10){
            ResponseEntity<ContentResponseDTO[]> results = this.newsLetterAPI.getContents(loopInPage,20, strategy );

            if (results.getBody().length==0)
                throw new RuntimeException("Lista vazia");

            Arrays.stream(results.getBody())
                    .filter(c -> c.title().toLowerCase().contains("pitch"))
                    .forEach(c -> response.add(this.contentMapper.responseToResponseSimplified(c)));

            loopInPage++;
        }


        return response;
    }

    public ContentResponseSimplifiedDTO getContent(ContentRequestDTO contentRequestDTO){
        ResponseEntity<ContentResponseDTO> response = this.newsLetterAPI.getContent(contentRequestDTO.slug(), contentRequestDTO.ownerName());

        if (response.getStatusCode()!=HttpStatus.OK) throw new RuntimeException("Deu ruin");

        return this.contentMapper.responseToResponseSimplified(response.getBody());
    }


}
