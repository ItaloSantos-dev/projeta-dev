package santzin.projeta.dev.infra.external;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import santzin.projeta.dev.DTOs.content.ContentResponseDTO;

import java.util.HashMap;
import java.util.Map;

@Component
public class NewsLetterAPI {
    private final RestTemplate restTemplate = new RestTemplate();

    private final String urlBase = "https://www.tabnews.com.br/api/v1";

    public ResponseEntity<ContentResponseDTO[]> getContents(int page, int perPage, String strategy){

        Map<String, Object> params = new HashMap<>();
        params.put("page", page);
        params.put("perPage", perPage);
        params.put("strategy", strategy);

        String urlFinal = urlBase+"/contents?page={page}&per_page={perPage}&strategy={strategy}";

        return restTemplate.getForEntity(urlFinal, ContentResponseDTO[].class, params);
    }


}
