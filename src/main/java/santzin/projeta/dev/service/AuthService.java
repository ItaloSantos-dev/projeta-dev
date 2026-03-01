package santzin.projeta.dev.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import santzin.projeta.dev.DTOs.auth.LoginRequestDTO;
import santzin.projeta.dev.DTOs.auth.RegisterRequestDTO;
import santzin.projeta.dev.DTOs.user.UserResponseDTO;
import santzin.projeta.dev.mapper.UserMapper;
import santzin.projeta.dev.model.UserModel;
import santzin.projeta.dev.repository.UserRepository;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserMapper userMapper;

    public String login(LoginRequestDTO requestDTO){
        Authentication usernamePassword = new UsernamePasswordAuthenticationToken(requestDTO.email(), requestDTO.password());
        Authentication auth =  this.authenticationManager.authenticate(usernamePassword);

        return tokenService.generateToken((UserModel) auth.getPrincipal());
    }

    public UserResponseDTO register(RegisterRequestDTO requestDTO){
        if(this.userRepository.existsByEmail(requestDTO.email())) throw new RuntimeException("Esta email ja esta cadatrado");
        UserModel newUser = userMapper.requestRegisterToModel(requestDTO);
        return this.userMapper.modelToResponse(this.userRepository.save(newUser));

    }
}
