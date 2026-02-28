package santzin.projeta.dev.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import santzin.projeta.dev.DTOs.project_position.ProjectPositionResponseDTO;
import santzin.projeta.dev.model.UserModel;
import santzin.projeta.dev.service.ProjectPositionService;

@RestController
@RequestMapping("positions")
public class PorjectPositionController {
    @Autowired
    private ProjectPositionService projectPositionService;

    @GetMapping("/{id}")
    public ResponseEntity<ProjectPositionResponseDTO> getPosition(@PathVariable Long id){
        return ResponseEntity.ok(this.projectPositionService.getById(id));
    }

    @PostMapping("/{id}")
    public ResponseEntity<ProjectPositionResponseDTO> createPositionOfProject(
            @RequestBody String name, @AuthenticationPrincipal UserModel user,
            @PathVariable Long id
    ){
        return ResponseEntity.ok(this.projectPositionService.createProjectPosition(name, id, user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjectPositionResponseDTO> updateById(
            @PathVariable Long id,
            @RequestBody String name,
            @AuthenticationPrincipal UserModel user
    ){
        return ResponseEntity.ok(this.projectPositionService.updateById(id, name, user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteyId(@PathVariable Long id, @AuthenticationPrincipal UserModel user){
        this.projectPositionService.deleteById(id, user);
        return ResponseEntity.ok().build();
    }

}
