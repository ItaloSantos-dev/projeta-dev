package santzin.projeta.dev.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import santzin.projeta.dev.DTOs.auth.LoginRequestDTO;
import santzin.projeta.dev.DTOs.auth.RegisterRequestDTO;
import santzin.projeta.dev.DTOs.user.UserResponseDTO;
import santzin.projeta.dev.service.AuthService;

@RestController
@RequestMapping("auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDTO loginRequestDTO){
        return ResponseEntity.ok(this.authService.login(loginRequestDTO));
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> register(@RequestBody RegisterRequestDTO registerRequestDTO){
        return ResponseEntity.ok(this.authService.register(registerRequestDTO));
    }
}
