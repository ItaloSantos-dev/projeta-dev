package santzin.projeta.dev.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import santzin.projeta.dev.DTOs.ContentResponseSimplifiedDTO;
import santzin.projeta.dev.DTOs.ContentResponseDTO;
import santzin.projeta.dev.infra.external.NewsLetterAPI;
import santzin.projeta.dev.mapper.ContentMapper;

import java.util.Arrays;
import java.util.List;

@Service
public class PitchService {
    @Autowired
    private NewsLetterAPI newsLetterAPI;

    @Autowired
    private ContentMapper contentMapper;

    public List<ContentResponseSimplifiedDTO> getMostPopularPitchs(){
        ResponseEntity<ContentResponseDTO[]> response = this.newsLetterAPI.getContents(1, 50, "relevant");
        if(response.getStatusCode()!= HttpStatus.OK) throw new RuntimeException("Deu ruin");

        return Arrays.stream(response.getBody())
                .filter(c -> c.title().toLowerCase().contains("pitch"))
                .map( c -> contentMapper.responseToResponseSimplified(c))
                .toList();
    }


}
