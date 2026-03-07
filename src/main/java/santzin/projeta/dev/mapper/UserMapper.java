package santzin.projeta.dev.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import santzin.projeta.dev.DTOs.auth.RegisterRequestDTO;
import santzin.projeta.dev.DTOs.project.ProjectResponseDTO;
import santzin.projeta.dev.DTOs.user.UserResponseDTO;
import santzin.projeta.dev.model.UserModel;
import santzin.projeta.dev.model.enums.UserRole;

import java.time.LocalDate;
import java.util.List;

@Component
public class UserMapper {
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    private ProjectMapper projectMapper;

    public UserModel requestRegisterToModel(RegisterRequestDTO request) {
        UserModel user = new UserModel();

        user.setName(request.name());
        user.setUsernameProperty(request.username());
        user.setEmail(request.email());
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setTelephoneNumber(request.telephoneNumber());
        user.setExperienceLevel(request.experienceLevel());
        user.setPrincipalStack(request.principalStack());

        user.setRole(UserRole.USER);
        user.setCreatedAt(LocalDate.now());

        return user;
    }

    public UserResponseDTO modelToResponse(UserModel userModel){
        List<ProjectResponseDTO> myProjects = userModel.getMyProjects().isEmpty()? null :
                userModel.getMyProjects().stream()
                        .map( p-> this.projectMapper.modelToResponse(p))
                        .toList();

        return new UserResponseDTO(
                userModel.getName(),
                userModel.getUsernameProperty(),
                userModel.getExperienceLevel(),
                userModel.getPrincipalStack(),
                myProjects
        );
    }
}
