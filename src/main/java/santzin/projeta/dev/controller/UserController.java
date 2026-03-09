package santzin.projeta.dev.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import santzin.projeta.dev.DTOs.hability.HabilityResponseDTO;
import santzin.projeta.dev.DTOs.project.ProjectResponseDTO;
import santzin.projeta.dev.DTOs.user.UserResponseDTO;
import santzin.projeta.dev.model.UserModel;
import santzin.projeta.dev.repository.UserRepository;
import santzin.projeta.dev.service.ProjectService;
import santzin.projeta.dev.service.UserService;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {
    @Autowired
    private UserService userService;


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




}
