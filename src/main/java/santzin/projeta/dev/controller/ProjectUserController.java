package santzin.projeta.dev.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import santzin.projeta.dev.DTOs.project_user.CreateProjectUserDTO;
import santzin.projeta.dev.DTOs.project_user.ProjectUserResponseDTO;
import santzin.projeta.dev.model.ProjectModel;
import santzin.projeta.dev.model.ProjectUserModel;
import santzin.projeta.dev.model.UserModel;
import santzin.projeta.dev.repository.ProjectRepository;
import santzin.projeta.dev.service.ProjectUserService;

@RestController
@RequestMapping("/projects-users")
public class ProjectUserController {
    @Autowired
    private ProjectUserService projectUserService;

    @Autowired
    private ProjectRepository projectRepository;

    @GetMapping("/{id}")
    public ResponseEntity<ProjectUserResponseDTO> getProjectUserById(@PathVariable Long id ){
        return ResponseEntity.ok(this.projectUserService.getProjectUserById(id));
    }

    @PostMapping
    public ResponseEntity<ProjectUserResponseDTO> createProjectUser(@RequestBody CreateProjectUserDTO createProjectUserDTO, @AuthenticationPrincipal UserModel user){
        System.out.println(createProjectUserDTO);
        return ResponseEntity.ok(this.projectUserService.createProjectUser(createProjectUserDTO, user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteByProjectIdAndUserId(@PathVariable Long id, @AuthenticationPrincipal  UserModel user){
        this.projectUserService.deleteProjectUserById(id, user);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjectUserResponseDTO> updateById(
            @PathVariable Long id,
            @RequestBody Long positionId,
            @AuthenticationPrincipal UserModel user
    ){
        return ResponseEntity.ok(this.projectUserService.updateById(id, positionId, user));
    }
}
