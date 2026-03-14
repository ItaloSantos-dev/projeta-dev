package santzin.projeta.dev.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import santzin.projeta.dev.DTOs.project_request.UpdateProjectRequestRequestDTO;
import santzin.projeta.dev.DTOs.project_request_notification.ProjectRequestNotificationResponseDTO;
import santzin.projeta.dev.model.UserModel;
import santzin.projeta.dev.service.ProjectRequestNotificationService;

import java.util.List;

@RestController
@RequestMapping("project-request-notification")
public class ProjectRequestNotificationController {

    @Autowired
    private ProjectRequestNotificationService projectRequestNotificationService;

    @GetMapping
    public ResponseEntity<List<ProjectRequestNotificationResponseDTO>> getAllNotifications(@AuthenticationPrincipal UserModel userModel){
        return ResponseEntity.ok(this.projectRequestNotificationService.getAllNotifications(userModel));
    }


}
