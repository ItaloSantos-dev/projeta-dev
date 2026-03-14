package santzin.projeta.dev.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import santzin.projeta.dev.DTOs.project.CreateProjectRequestDTO;
import santzin.projeta.dev.DTOs.project.MyProjectsResponseDTO;
import santzin.projeta.dev.DTOs.project.ProjectResponseDTO;
import santzin.projeta.dev.DTOs.project.UpdateProjectRequestDTO;
import santzin.projeta.dev.DTOs.project_request.ProjectRequestResponseDTO;
import santzin.projeta.dev.DTOs.project_request.UpdateProjectRequestRequestDTO;
import santzin.projeta.dev.DTOs.project_request_notification.ProjectRequestNotificationResponseDTO;
import santzin.projeta.dev.model.UserModel;
import santzin.projeta.dev.service.ProjectRequestNotificationService;
import santzin.projeta.dev.service.ProjectRequestService;
import santzin.projeta.dev.service.ProjectService;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @Autowired
    private ProjectRequestService projectRequestService;

    @Autowired
    private ProjectRequestNotificationService projectRequestNotificationService;

    @GetMapping
    public ResponseEntity<MyProjectsResponseDTO> getMyProjects(@AuthenticationPrincipal UserModel user){
        return ResponseEntity.ok(this.projectService.getMyProjects(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectResponseDTO> getProjectById(@PathVariable Long id){
        return ResponseEntity.ok(this.projectService.getById(id));
    }

    @PostMapping
    public ResponseEntity<ProjectResponseDTO> createProject(
            @RequestBody CreateProjectRequestDTO createProjectRequestDTO,
            @AuthenticationPrincipal UserModel user
            ){
        return ResponseEntity.ok(this.projectService.createProject(createProjectRequestDTO, user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable Long id, @AuthenticationPrincipal UserModel user){
        this.projectService.deleteById(id, user);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjectResponseDTO> updateById(
            @PathVariable Long id,
            @RequestBody UpdateProjectRequestDTO updateProjectRequestDTO,
            @AuthenticationPrincipal UserModel user
    ){
        return ResponseEntity.ok(this.projectService.UpdateById(id, updateProjectRequestDTO, user));
    }


    @PutMapping("/requests/{projectRequestId}")
    public ResponseEntity<Void> acceptedProjectRequest(
            @AuthenticationPrincipal UserModel userModel,
            @PathVariable Long projectRequestId,
            @RequestBody UpdateProjectRequestRequestDTO requestDTO
    ){
        this.projectRequestService.updateStatus(userModel, projectRequestId, requestDTO.newStatus(), requestDTO.positionId());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/requests")
    public ResponseEntity<ProjectRequestResponseDTO> createProjectRequest(@AuthenticationPrincipal UserModel user, @PathVariable Long id){
        return ResponseEntity.ok(this.projectRequestService.create(user, id));
    }
    @GetMapping("/{id}/requests")
    public ResponseEntity<List<ProjectRequestNotificationResponseDTO>> getNotificationsRequestsOfProjectById(
            @PathVariable Long id,
            @AuthenticationPrincipal UserModel user
    ){
        return ResponseEntity.ok(this.projectRequestNotificationService.getNotificationsRequestsOfProjectById(id, user));
    }



}
