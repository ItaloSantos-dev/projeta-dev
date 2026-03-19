package santzin.projeta.dev.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import santzin.projeta.dev.DTOs.hability.HabilityResponseDTO;
import santzin.projeta.dev.DTOs.project.ProjectResponseDTO;
import santzin.projeta.dev.DTOs.project_request_notification.ProjectRequestNotificationResponseDTO;
import santzin.projeta.dev.DTOs.user.UserResponseDTO;
import santzin.projeta.dev.model.UserModel;
import santzin.projeta.dev.repository.UserRepository;
import santzin.projeta.dev.service.ProjectRequestNotificationService;
import santzin.projeta.dev.service.ProjectService;
import santzin.projeta.dev.service.UserService;
import santzin.projeta.dev.service.UsersFollowService;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private ProjectRequestNotificationService projectRequestNotificationService;

    @Autowired
    private UsersFollowService usersFollowService;


    @GetMapping("/{username}")
    public ResponseEntity<UserResponseDTO> getUserByUsername(@PathVariable String username){
        return ResponseEntity.ok(this.userService.getUserByUsername(username));
    }

    @GetMapping("/{username}/{slugProject}")
    public ResponseEntity<ProjectResponseDTO> getProjectOfUserByUsernameAndSlug(
            @PathVariable String username,
            @PathVariable String  slugProject
    ){
        return ResponseEntity.ok(this.userService.getProjectOfUserByUsernameAndSlug(username, slugProject));
    }

    @GetMapping("/{username}/projects")
    public ResponseEntity<List<ProjectResponseDTO>> getProjectsOfUserByUsername(@PathVariable String username){
        return ResponseEntity.ok(this.userService.getProjectsOfUserByUsername(username));
    }

    @GetMapping("/{username}/habilitys")
    public List<HabilityResponseDTO> getHabilitys (@PathVariable String username){
        return this.userService.getHabilitys(username);
    }

    @GetMapping("/notifications")
    public ResponseEntity<List<ProjectRequestNotificationResponseDTO>> getNotificationsByAuthentication(
            @AuthenticationPrincipal UserModel userModel
    ){
        return ResponseEntity.ok(this.projectRequestNotificationService.getNotificationsRequestsOfuserById(userModel));
    }

    @PutMapping("/notifications/{id}")
    public void setReadNotificationById(@PathVariable  Long id){
        this.projectRequestNotificationService.setReadNotificationById(id);
    }

    @GetMapping("/{username}/following")
    public ResponseEntity<List<UserResponseDTO>> getFollowingOfUserByUsername(@PathVariable String username){
        return ResponseEntity.ok(this.usersFollowService.getFollowingOfUserByUsername(username));
    }
    @PostMapping("/following/{id}")
    public ResponseEntity<Void> createProjectRequest(@AuthenticationPrincipal UserModel user, @PathVariable Long id){
        this.usersFollowService.followUserById(user, id);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/following/{id}")
    public ResponseEntity<List<UserResponseDTO>> getFollowingOfUserByUsername(
            @AuthenticationPrincipal UserModel user,
            @PathVariable Long id
            ){
        this.usersFollowService.deleteUserFollowByUserFollowedId(user, id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{username}/followers")
    public ResponseEntity<List<UserResponseDTO>> getFollowersOfUserByUsername(@PathVariable String username){
        return ResponseEntity.ok(this.usersFollowService.getFollowersOfUserByUsername(username));
    }



}
