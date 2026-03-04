package santzin.projeta.dev.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import santzin.projeta.dev.DTOs.content.ContentRequestDTO;
import santzin.projeta.dev.DTOs.content.ContentResponseDTO;
import santzin.projeta.dev.DTOs.content.ContentResponseSimplifiedDTO;
import santzin.projeta.dev.model.enums.StrategyFindContents;
import santzin.projeta.dev.service.ContentService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("contents")
public class ContentController {
    @Autowired
    private ContentService contentService;

    @GetMapping("/most-popular")
    private ResponseEntity<List<ContentResponseSimplifiedDTO>> getMostPopularContents(
            @RequestParam Integer page,
            @RequestParam StrategyFindContents strategy,
            @RequestParam(required = false) UUID lastContentId
    ){
        return ResponseEntity.ok(this.contentService.getMostPopularContents(page, lastContentId, strategy));
    }

    @PostMapping()
    private ResponseEntity<ContentResponseSimplifiedDTO> getContent(@RequestBody ContentRequestDTO contentRequestDTO){
        return ResponseEntity.ok(this.contentService.getContent(contentRequestDTO));
    }
}
